package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Article> selectAllArticle(Integer page, Integer rows) {
        List<Article> articles = articleDao.selectAllArticle(page, rows);
        return articles;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Integer totalCount() {
        Integer integer = articleDao.totalCount();
        return integer;
    }

    @Override
    public String save(Article article) {
        String uuid = UUID.randomUUID().toString();
        article.setId(uuid);
        article.setUp_date(new Date());
        articleDao.save(article);
        return uuid;
    }

    @Override
    public String update(Article article) {
//        System.out.println(99999999);
//        if (employee.getSex().equals("1")){employee.setSex("男");}
//        if (employee.getSex().equals("2")){employee.setSex("女");}
        articleDao.update(article);
        return article.getId();
    }

    @Override
    public void delete(String id) {
        articleDao.delete(id);
    }
}
