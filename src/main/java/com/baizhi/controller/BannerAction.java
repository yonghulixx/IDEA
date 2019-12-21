package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RestController
@RequestMapping("/banner")
public class BannerAction {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/selectAll")
    @ResponseBody
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        List<Banner> banner = bannerService.selectAllBanner(page, rows);
        Integer total = bannerService.totalCount();
        Map<String, Object> maps = new HashMap<>();
        maps.put("page", page);
        maps.put("records", total);
        Integer pageCount = 0;
        if (total % rows == 0) {
            pageCount = total / rows;
        } else {
            pageCount = total / rows + 1;
        }
        maps.put("total", pageCount);
        maps.put("rows", banner);
        return maps;
    }

    @RequestMapping("updateStatus")
    @ResponseBody
    public HashMap<String, Object> updateStatus(String id, String status) {
        HashMap<String, Object> map = new HashMap<>();
        Banner banner = new Banner();
        if (status.equals("1")) {
            banner.setStatus("1");
        } else {
            banner.setStatus("2");
        }
        banner.setId(id);

        bannerService.update(banner);
        map.put("success", "200");
        map.put("message", "修改成功");
        return map;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Banner banner, String oper) {
        //       System.out.println(banner);
//        System.out.println(oper);
        String uid = null;
        if ("add".equals(oper)) {
            uid = bannerService.save(banner);
        } else if ("edit".equals(oper)) {
            uid = bannerService.update(banner);
        } else if ("del".equals(oper)) {
            bannerService.delete(banner.getId());
        }
        //System.out.println(123+uid);
        return uid;
//        if("add".equals(oper)){
//            employeeService.save(employee);
//        }

        /*return "";*/
    }

    @RequestMapping("uploadBanner")
    public void uploadBanner(MultipartFile img_path, String id, HttpServletRequest request) {
        //System.out.println("--------upload----------"+id);
        String realPath = request.getSession().getServletContext().getRealPath("/bootstrap/imgs");
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = img_path.getOriginalFilename();

        String name = new Date().getTime() + "_" + filename;

        try {
            img_path.transferTo(new File(realPath, filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Banner banner = new Banner();
        banner.setId(id);
        banner.setImg_path(filename);
        bannerService.update(banner);
    }
}
