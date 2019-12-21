package com.baizhi.dao;

import com.baizhi.entity.City;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    public List<User> selectAllUser(@Param("page") Integer page, @Param("rows") Integer rows);

    public Integer totalCount();

    public void save(User user);

    public void update(User user);

    public void delete(String id);

    public List<User> selectAll();

    public List<Month> selectByMonthN();

    public List<Month> selectByMonthV();

    public List<City> selectByCityN();

    public List<City> selectByCityV();
}
