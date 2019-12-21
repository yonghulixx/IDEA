package com.baizhi;

import com.alibaba.fastjson.JSONObject;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGoEasy {
    @Test
    public void test() {
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-df0a45499f274b2bae29ae50a6a12dc9");
        goEasy.publish("mychannel", "Hello, GoEasy!");
    }

    @Test
    public void testGoEasy() {
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            random.nextInt(10);

            int[] randoms = {random.nextInt(200), random.nextInt(100), random.nextInt(600), random.nextInt(300), random.nextInt(500), random.nextInt(700)};

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("month", Arrays.asList("1", "2", "3", "4", "5", "6"));
            jsonObject.put("boys", randoms);
            jsonObject.put("girls", randoms);

            String content = jsonObject.toString();


            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-df0a45499f274b2bae29ae50a6a12dc9");
            goEasy.publish("mychannel", content);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
