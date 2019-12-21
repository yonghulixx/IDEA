package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.entity.Student;
import com.baizhi.entity.Teacher;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
public class TestEasyPoi {
    @Test
    public void tesstpoi() {
        ArrayList<Student> stus = new ArrayList<>();

        stus.add(new Student("1", "src/main/webapp/bootstrap/imgs/214232.jpg", 12, new Date()));
        stus.add(new Student("2", "小白2", 14, new Date()));
        stus.add(new Student("3", "小白3", 12, new Date()));
        stus.add(new Student("4", "小白4", 14, new Date()));
        stus.add(new Student("5", "F:/user/QQ音乐/QQMusicCache/QQMusicPicture/Ariana Grande_boyfriend (Explicit)_4.jpg", 12, new Date()));

        ArrayList<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("1", "suns", stus));
        teachers.add(new Teacher("2", "cx", stus));

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生"), Teacher.class, teachers);
        try {
            workbook.write(new FileOutputStream(new File("F://CSS/easypoi.xls")));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testpoiImport() {
        //创建导入对象
        ImportParams params = new ImportParams();
        params.setTitleRows(1); //表格标题行数,默认0
        params.setHeadRows(2);  //表头行数,默认1

        //获取导入数据
        try {
            List<Teacher> teachers = ExcelImportUtil.importExcel(new FileInputStream(new File("F://CSS/easypoi.xls")), Teacher.class, params);
            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
