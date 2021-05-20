package com.enlightenment.demo;

import com.enlightenment.demo.service.otherservice.CryptoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
class EnlightenmentDemoApplicationTests {

    @Autowired
    CryptoService service;

    @Test
    void contextLoads() throws Exception {
        File file = new File("C:\\Users\\Administrator\\Desktop\\常用命令.txt");
//        System.out.println(hashFile(file));
    }




}
