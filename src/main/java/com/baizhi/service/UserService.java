package com.baizhi.service;


import com.baizhi.entity.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    public List<User> selectAllUser(Integer page, Integer rows);

    public Integer totalCount();

    public void save(User user);

    public void update(User user);

    public void delete(String id);

    public List<User> selectAll();

    public HashMap<String, Object> selectByMonth();

    public List<Object> selectByCity();

}
