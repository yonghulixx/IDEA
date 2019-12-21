package com.baizhi.entity;

public class City {
    private String sex;
    private String city;
    private Integer count;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public City(String sex, String city, Integer count) {
        this.sex = sex;
        this.city = city;
        this.count = count;
    }

    public City() {
    }

    @Override
    public String toString() {
        return "City{" +
                "sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", count=" + count +
                '}';
    }
}
