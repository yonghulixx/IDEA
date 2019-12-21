package com.baizhi.service;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Resource
    private ChapterDao chapterDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Chapter> selectAllChapter(String albumId, Integer page, Integer rows) {
        List<Chapter> chapters = chapterDao.selectAllChapter(albumId, page, rows);
        return chapters;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Integer totalCount() {
        Integer integer = chapterDao.totalCount();
        return integer;
    }

    @Override
    public String save(Chapter chapter) {
//        employee.setId(UUID.randomUUID().toString());
//        if (employee.getSex().equals("1")){employee.setSex("男");}
//        if (employee.getSex().equals("2")){employee.setSex("nv");}
        String uuid = UUID.randomUUID().toString();
        chapter.setId(uuid);
        chapter.setUp_date(new Date());
        chapterDao.save(chapter);
        return uuid;
    }

    @Override
    public String update(Chapter chapter) {
//        System.out.println(99999999);
//        if (employee.getSex().equals("1")){employee.setSex("男");}
//        if (employee.getSex().equals("2")){employee.setSex("女");}
        String id = chapter.getId();
        chapterDao.update(chapter);
        return id;
    }

    @Override
    public void delete(String id) {
        chapterDao.delete(id);
    }
}
