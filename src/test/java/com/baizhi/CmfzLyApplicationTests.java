package com.baizhi;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.Month;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzLyApplicationTests {
    @Autowired
    private UserDao userDao;

    @Test
    public void test1() {
        List<Month> months = userDao.selectByMonthN();
        for (Month month : months) {
            System.out.println(month);
        }
    }

    @Test
    public void contextLoads() {
    }

}
