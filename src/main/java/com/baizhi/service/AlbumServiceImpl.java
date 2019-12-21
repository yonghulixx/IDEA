package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Resource
    private AlbumDao albumDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Album> selectAllAlbum(Integer page, Integer rows) {
        List<Album> albums = albumDao.selectAllAlbum(page, rows);
        return albums;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Integer totalCount() {
        Integer integer = albumDao.totalCount();
        return integer;
    }

    @Override
    public String save(Album album) {
        String uuid = UUID.randomUUID().toString();
        album.setId(uuid);
        albumDao.save(album);
        return uuid;
    }

    @Override
    public String update(Album album) {
        //System.out.println(99999999);
        albumDao.update(album);
        return album.getId();
    }

    @Override
    public void delete(String id) {
        albumDao.delete(id);
    }
}
