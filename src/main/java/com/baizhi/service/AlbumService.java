package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;

public interface AlbumService {
    public List<Album> selectAllAlbum(Integer page, Integer rows);

    public Integer totalCount();

    public String save(Album album);

    public String update(Album album);

    public void delete(String id);


}
