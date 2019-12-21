package com.baizhi.controller;

import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/guru")
public class GuruAction {
    @Autowired
    private GuruService guruService;

    @RequestMapping("/selectAll")
    @ResponseBody
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        List<Guru> gurus = guruService.selectAllGuru((page - 1) * rows, rows);
        Integer total = guruService.totalCount();
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("page", page);
        maps.put("records", total);
        Integer pageCount = 0;
        if (total % rows == 0) {
            pageCount = total / rows;
        } else {
            pageCount = total / rows + 1;
        }
        maps.put("total", pageCount);
        maps.put("rows", gurus);
        return maps;
    }

    @RequestMapping("updateStatus")
    @ResponseBody
    public HashMap<String, Object> updateStatus(String id, String status) {
        HashMap<String, Object> map = new HashMap<>();
        Guru guru = new Guru();
        if (status.equals("1")) {
            guru.setStatus("1");
        } else {
            guru.setStatus("2");
        }
        guru.setId(id);

        guruService.update(guru);
        map.put("success", "200");
        map.put("message", "修改成功");
        return map;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public void edit(Guru guru, String oper) {
        System.out.println(guru);
        System.out.println(oper);
        if ("add".equals(oper)) {
            guruService.save(guru);
        } else if ("edit".equals(oper)) {
            guruService.update(guru);
        } else if ("del".equals(oper)) {
            guruService.delete(guru.getId());
        }

//        if("add".equals(oper)){
//            employeeService.save(employee);
//        }

        /*return "";*/
    }
}
