package com.baizhi.entity;

public class Month {
    private String sex;
    private String month;
    private Integer count;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Month() {
    }

    public Month(String sex, String month, Integer count) {
        this.sex = sex;
        this.month = month;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Month{" +
                "sex='" + sex + '\'' +
                ", month='" + month + '\'' +
                ", count=" + count +
                '}';
    }
}
