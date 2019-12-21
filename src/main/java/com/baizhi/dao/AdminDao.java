package com.baizhi.dao;

import com.baizhi.entity.Admin;

public interface AdminDao {
    public void regist(Admin admin);

    public Admin login(String name);


}
