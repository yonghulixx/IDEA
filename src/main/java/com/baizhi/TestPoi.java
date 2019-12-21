package com.baizhi;

import com.baizhi.entity.Student;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
public class TestPoi {
    @Test
    public void tesstpoi() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("用户表");

        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);

        cell.setCellValue("一个单元格");

        try {
            workbook.write(new FileOutputStream(new File("F://testpoi.xls")));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tesstpoiExport() {
        Student s1 = new Student("1", "小白1", 12, new Date());
        Student s2 = new Student("2", "小白2", 14, new Date());
        Student s3 = new Student("3", "小白3", 12, new Date());
        Student s4 = new Student("4", "小白4", 14, new Date());
        Student s5 = new Student("5", "小白5", 12, new Date());

        List<Student> ss = Arrays.asList(s1, s2, s3, s4, s5);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("用户表");

        Row row2 = sheet.createRow(0);
        row2.createCell(0).setCellValue("学生信息");
        CellRangeAddress address = new CellRangeAddress(2, 7, 5, 5);
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

        for (int i = 0; i < ss.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 2);
            Cell cell = row1.createCell(0);
            cell.setCellValue(ss.get(i).getId());
            row1.createCell(1).setCellValue(ss.get(i).getName());
            row1.createCell(2).setCellValue(ss.get(i).getAge());
            HSSFCell cell1 = row1.createCell(3);
            cell1.setCellStyle(cellStyle);
            cell1.setCellValue(ss.get(i).getBir());
        }
        try {
            workbook.write(new FileOutputStream(new File("F://CSS/testpoi.xls")));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void tesstPoiImport() {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("F://CSS/testpoi.xls")));

            HSSFSheet sheet = workbook.getSheet("用户表");

            for (int i = 2; i <= sheet.getLastRowNum(); i++) {

                HSSFRow row = sheet.getRow(i);
                Student student = new Student();

                HSSFCell cell = row.getCell(0);
                String id = cell.getStringCellValue();
                student.setId(id);
                student.setName(row.getCell(1).getStringCellValue());
                double age = row.getCell(2).getNumericCellValue();
                student.setAge((int) age);
                student.setBir(row.getCell(3).getDateCellValue());

                System.out.println(student);
            }
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
