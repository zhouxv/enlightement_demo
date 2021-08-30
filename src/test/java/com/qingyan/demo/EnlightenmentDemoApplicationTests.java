package com.qingyan.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
class EnlightenmentDemoApplicationTests {


    public static void main(String[] args) {
        File file = new File("E:\\水星系统归档\\数据确权\\原型实现v2\\后端设计\\接口流程概述.xlsx");
        System.out.println(file.exists());
    }

    @Test
    void contextLoads() {
    }


}
