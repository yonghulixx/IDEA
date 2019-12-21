package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface AdminService {
    public void modifyregist(Admin admin);

    public HashMap<String, Object> login(Admin admin, String enCode, HttpSession session);
}
