package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    public List<Article> selectAllArticle(@Param("page") Integer page, @Param("rows") Integer rows);

    public Integer totalCount();

    public void save(Article article);

    public void update(Article article);

    public void delete(String id);
}
