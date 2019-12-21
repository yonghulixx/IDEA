package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class AdminAction {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(Admin admin) {
        adminService.modifyregist(admin);
        return "front/login";
    }

    @RequestMapping("/getImageCode")
    public void getImgageCode(HttpServletResponse response, HttpSession session) {
        String code = ImageCodeUtil.getSecurityCode();
        session.setAttribute("ImageCode", code);
        BufferedImage image = ImageCodeUtil.createImage(code);
        response.setContentType("image/png");
        try {
            ImageIO.write(image, "png", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public HashMap<String, Object> login(Admin admin, String enCode, HttpSession session) {


        HashMap<String, Object> map = adminService.login(admin, enCode, session);
//        System.out.println(map);
        return map;
    }

    @RequestMapping("/exit")
    public String exit(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        session.invalidate();
        return "front/login";
    }
}
