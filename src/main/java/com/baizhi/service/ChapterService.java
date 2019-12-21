package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.List;

public interface ChapterService {
    public List<Chapter> selectAllChapter(String albumId, Integer page, Integer rows);

    public Integer totalCount();

    public String save(Chapter chapter);

    public String update(Chapter chapter);

    public void delete(String id);


}
