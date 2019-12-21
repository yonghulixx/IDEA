package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.List;

public interface ArticleService {
    public List<Article> selectAllArticle(Integer page, Integer rows);

    public Integer totalCount();

    public String save(Article employee);

    public String update(Article employee);

    public void delete(String id);


}
