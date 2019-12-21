package com.baizhi.service;

import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Guru;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {
    @Resource
    private GuruDao guruDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Guru> selectAllGuru(Integer page, Integer rows) {
        List<Guru> gurus = guruDao.selectAllGuru(page, rows);
        return gurus;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Integer totalCount() {
        Integer integer = guruDao.totalCount();
        return integer;
    }

    @Override
    public void save(Guru guru) {
//        guru.setId(UUID.randomUUID().toString());
//        if (employee.getSex().equals("1")){employee.setSex("男");}
//        if (employee.getSex().equals("2")){employee.setSex("nv");}
        guruDao.save(guru);
    }

    @Override
    public void update(Guru employee) {
        //   System.out.println(99999999);
//        if (employee.getSex().equals("1")){equalsmployee.setSex("男");}
//        if (employee.getSex().equals("2")){employee.setSex("女");}
        guruDao.update(employee);
    }

    @Override
    public void delete(String id) {
        guruDao.delete(id);
    }
}
