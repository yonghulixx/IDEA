package com.baizhi.entity;

import java.util.Date;

public class Guru {
    private String id;
    private String name;
    private String pic_img;
    private String status;
    private Date reg_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic_img() {
        return pic_img;
    }

    public void setPic_img(String pic_img) {
        this.pic_img = pic_img;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Guru(String id, String name, String pic_img, String status, Date reg_date) {
        this.id = id;
        this.name = name;
        this.pic_img = pic_img;
        this.status = status;
        this.reg_date = reg_date;
    }

    public Guru() {
    }

    @Override
    public String toString() {
        return "Guru{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pic_img='" + pic_img + '\'' +
                ", status='" + status + '\'' +
                ", reg_date=" + reg_date +
                '}';
    }
}
