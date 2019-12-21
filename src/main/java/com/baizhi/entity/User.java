package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;

import java.util.Date;

public class User {
    @Excel(name = "ID")
    private String id;
    @Excel(name = "手机号")
    private String phone;
    @Excel(name = "密码")
    private String password;
    @Excel(name = "盐")
    private String salt;
    @Excel(name = "头像")
    private String pic_img;
    @Excel(name = "法名")
    private String ahama;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "ID")
    private String city;
    @Excel(name = "签名")
    private String sign;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "注册时间", format = ("yyyy-MM-dd"))
    private Date reg_date;
    @Excel(name = "上师ID")
    private String guruId;
    @ExcelIgnore
    private String month;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPic_img() {
        return pic_img;
    }

    public void setPic_img(String pic_img) {
        this.pic_img = pic_img;
    }

    public String getAhama() {
        return ahama;
    }

    public void setAhama(String ahama) {
        this.ahama = ahama;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public String getGuruId() {
        return guruId;
    }

    public void setGuruId(String guruId) {
        this.guruId = guruId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public User(String id, String phone, String password, String salt, String pic_img, String ahama, String name, String sex, String city, String sign, String status, Date reg_date, String guruId, String month) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.salt = salt;
        this.pic_img = pic_img;
        this.ahama = ahama;
        this.name = name;
        this.sex = sex;
        this.city = city;
        this.sign = sign;
        this.status = status;
        this.reg_date = reg_date;
        this.guruId = guruId;
        this.month = month;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", pic_img='" + pic_img + '\'' +
                ", ahama='" + ahama + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", sign='" + sign + '\'' +
                ", status='" + status + '\'' +
                ", reg_date=" + reg_date +
                ", guruId='" + guruId + '\'' +
                ", month='" + month + '\'' +
                '}';
    }
}
