package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserAction {
    @Autowired
    private UserService userService;

    @RequestMapping("/selectAll")
    @ResponseBody
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        List<User> users = userService.selectAllUser((page - 1) * rows, rows);
        Integer total = userService.totalCount();
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
        maps.put("rows", users);
        return maps;
    }

    @RequestMapping("updateStatus")
    @ResponseBody
    public HashMap<String, Object> updateStatus(String id, String status) {
        HashMap<String, Object> map = new HashMap<>();
        User user = new User();
        if (status.equals("1")) {
            user.setStatus("1");
        } else {
            user.setStatus("2");
        }
        user.setId(id);

        userService.update(user);
        map.put("success", "200");
        map.put("message", "修改成功");
        return map;
    }

    @RequestMapping("/edit")
    @ResponseBody
    public void edit(User user, String oper) {
        // System.out.println(user);
        //System.out.println(oper);
        if ("add".equals(oper)) {
            userService.save(user);
        } else if ("edit".equals(oper)) {
            userService.update(user);
        } else if ("del".equals(oper)) {
            userService.delete(user.getId());
        }

//        if("add".equals(oper)){
//            employeeService.save(employee);
//        }

        /*return "";*/
    }

    @RequestMapping("/poiExport")
    public void poiExport() {
        List<User> users = userService.selectAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("用户表");

        Row row2 = sheet.createRow(0);
        row2.createCell(0).setCellValue("用户信息");
        CellRangeAddress address = new CellRangeAddress(0, 0, 0, 7);
        sheet.addMergedRegion(address);

        sheet.setColumnWidth(3, 20 * 256);


        HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(Font.COLOR_RED);
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 24);
        font.setItalic(true);

        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setFont(font);


        Row row = sheet.createRow(1);
        row.setHeight((short) 900);

        String[] title = {"ID", "名字", "年龄", "生日"};
        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(cellStyle1);
        }
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy年MM月dd日"));

        for (int i = 0; i < users.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 2);
            Cell cell = row1.createCell(0);
            cell.setCellValue(users.get(i).getId());
            row1.createCell(1).setCellValue(users.get(i).getName());
            //row1.createCell(2).setCellValue(users.get(i).getAge());
            HSSFCell cell1 = row1.createCell(3);
            cell1.setCellStyle(cellStyle);
            //cell1.setCellValue(users.get(i).getBir());
        }
        try {
            workbook.write(new FileOutputStream(new File("F://CSS/testpoi.xls")));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/easyPoiExport")
    @ResponseBody
    public String easyPoiExport() {

        List<User> users = userService.selectAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息", "用户表"), User.class, users);
        try {
            System.out.println("F://CSS/用户信息.xls");
            workbook.write(new FileOutputStream(new File("F://CSS/用户信息.xls")));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping("/selectByMonth")
    @ResponseBody
    public HashMap<String, Object> selectByMonth() {
        HashMap<String, Object> map = userService.selectByMonth();
        System.out.println(map);
        return map;
    }

    @RequestMapping("/selectByCity")
    @ResponseBody
    public List<Object> selectByCity() {
        List<Object> list = userService.selectByCity();
        System.out.println(list);
        return list;
    }
}
