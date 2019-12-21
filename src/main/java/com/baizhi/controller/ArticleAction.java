package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/article")
public class ArticleAction {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/selectAll")
    @ResponseBody
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        List<Article> articles = articleService.selectAllArticle((page - 1) * rows, rows);
        Integer total = articleService.totalCount();
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("page", page);
        maps.put("records", total);
        Integer pageCount = 0;
        if (total % rows == 0) {
            pageCount = total / rows;
        } else {
            pageCount = total / rows + 1;
        }
        maps.put("total", pageCount);
        maps.put("rows", articles);
        return maps;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Article article, String oper) {
        System.out.println("----article----edit-----void-----");
        String uid = null;
        if ("add".equals(oper)) {
            uid = articleService.save(article);
        } else if ("edit".equals(oper)) {
            uid = articleService.update(article);
        } else if ("del".equals(oper)) {
            uid = null;
            articleService.delete(article.getId());

        }
        return uid;
//        if("add".equals(oper)){
//            employeeService.save(employee);
    }

    /*return "";*/


    @RequestMapping("update")
    public HashMap<String, Object> update(Article article, String ArticleId) {
        System.out.println("----article----update----------");

        article.setId(ArticleId);
        articleService.update(article);
        return null;
    }

    @RequestMapping("add")
    public HashMap<String, Object> add(Article article) {
        System.out.println("----article----add----------");
        articleService.save(article);
        return null;
    }

    @RequestMapping("upload")//imgFile
    public HashMap<String, Object> upload(MultipartFile imgFile, String id, HttpServletRequest request) {
        System.out.println("----article----upload1----------" + id);
        HashMap<String, Object> map = new HashMap<>();
        String realPath = request.getSession().getServletContext().getRealPath("/upload/editor");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = imgFile.getOriginalFilename();

        String name = new Date().getTime() + "_" + filename;

        String scheme = request.getScheme();
        int serverPort = request.getServerPort();
        String serverName = request.getServerName();
        String contextPath = request.getContextPath();

        String serverPath = scheme + "://" + serverName + ":" + serverPort + "/" + contextPath + "/upload/editor/" + name;

        try {
            imgFile.transferTo(new File(realPath, name));
            Article article = new Article();
            article.setInsert_img(name);
            articleService.update(article);
            map.put("error", 0);
            map.put("url", serverPath);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("message", "上传失败");
        }

        /*Article article = new Article();
        article.setId(id);
        article.setImgpath(filename);
        articleService.update(article);*/
        return map;
    }

    @RequestMapping("queryAllPhoto")
    public HashMap<String, Object> queryAllPhoto(HttpServletRequest request) {
        System.out.println("----article----upload2----------");
        HashMap<String, Object> maps = new HashMap<>();
        ArrayList<Object> lists = new ArrayList<>();
        String realPath = request.getSession().getServletContext().getRealPath("/upload/editor");
        File file = new File(realPath);
        String[] names = file.list();
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            //System.out.println(s);
            HashMap<String, Object> map = new HashMap<>();
            map.put("is_dir", false);
            map.put("has_file", false);
            File file1 = new File(realPath, name);
            map.put("filesize", file1.length());
            map.put("is_photo", true);
            String extension = FilenameUtils.getExtension(name);
            map.put("filetype", extension);
            map.put("filename", name);
            String[] split = name.split("-");
            String times = split[0];
            long time = Long.parseLong(times);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:ss:mm");
            String format = dateFormat.format(time);
            map.put("datetime", format);
            lists.add(map);

            Article article = new Article();
            article.setInsert_img(name);
            articleService.update(article);

        }
        /*if (!file.exists()) {
            file.mkdirs();
        }*/
        maps.put("current_url", "http://localhost:8989/cmfz/upload/editor/");
        maps.put("total_count", lists.size());
        maps.put("file_list", lists);


        return maps;
    }
}
