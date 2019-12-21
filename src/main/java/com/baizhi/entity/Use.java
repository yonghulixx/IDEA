package com.baizhi.entity;

public class Use {
    private String sex;
    private String month;
    private String city;
    private String count;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Use(String sex, String month, String city, String count) {
        this.sex = sex;
        this.month = month;
        this.city = city;
        this.count = count;
    }

    public Use() {
    }

    @Override
    public String toString() {
        return "Use{" +
                "sex='" + sex + '\'' +
                ", month='" + month + '\'' +
                ", city='" + city + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
