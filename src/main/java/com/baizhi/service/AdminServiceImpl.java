package com.baizhi.service;


import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.UUID;

//import org.apache.struts2.ServletActionContext;
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Override
    public void modifyregist(Admin admin) {
        admin.setId(UUID.randomUUID().toString());
        /*if (admin.getSex().equals("m")) {
            admin.setSex("男");
        }
        if (admin.getSex().equals("f")) {
            admin.setSex("女");
        }*/
        adminDao.regist(admin);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public HashMap<String, Object> login(Admin admin, String enCode, HttpSession session) {
        String code = (String) session.getAttribute("ImageCode");
        HashMap<String, Object> map = new HashMap<>();
        Admin a = null;
//         System.out.println(admin + enCode + code);
        if (code.equals(enCode)) {
            a = adminDao.login(admin.getName());
//            System.out.println(a);
            if (a != null/*dmin.getName().equals(a.getName())*/) {
                if (admin.getPassword().equals(a.getPassword())) {
                    map.put("success", "200");
                    map.put("message", "登陆成功");
//                    System.out.println(a + "-----786---------------------");
                    session.setAttribute("adminLogin", a);
                } else {
                    map.put("success", "400");
                    map.put("message", "密码错误");
                }
            } else {
                map.put("success", "400");
                map.put("message", "用户不存在");
            }
        }/*if (a .equals(null))*/ else {
            map.put("success", "400");
            map.put("message", "验证码错误");
        }

        return map;
    }
}
