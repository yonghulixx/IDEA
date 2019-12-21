package com.baizhi.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T> {
    List<T> queryByPage(@Param("start") Integer start, @Param("rows") Integer rows);

    void save(T t);//add

    void update(T t);

    void delete(String id);

    Integer totalCount();//queryByTotal
}
