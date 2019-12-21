package com.baizhi.entity;

import java.util.Date;

public class Article {
    private String id;
    private String title;
    private String insert_img;
    private String content;
    private Date up_date;
    private String guruId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInsert_img() {
        return insert_img;
    }

    public void setInsert_img(String insert_img) {
        this.insert_img = insert_img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    public String getGuruId() {
        return guruId;
    }

    public void setGuruId(String guruId) {
        this.guruId = guruId;
    }

    public Article(String id, String title, String insert_img, String content, Date up_date, String guruId) {
        this.id = id;
        this.title = title;
        this.insert_img = insert_img;
        this.content = content;
        this.up_date = up_date;
        this.guruId = guruId;
    }

    public Article() {
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", insert_img='" + insert_img + '\'' +
                ", content='" + content + '\'' +
                ", up_date=" + up_date +
                ", guruId='" + guruId + '\'' +
                '}';
    }
}
