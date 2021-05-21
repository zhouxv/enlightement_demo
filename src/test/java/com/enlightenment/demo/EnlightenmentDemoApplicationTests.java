package com.enlightenment.demo;

import com.enlightenment.demo.service.otherservice.CryptoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.enlightenment.demo.util.crypto.AESKeyGen.keyGen;

@SpringBootTest
@RunWith(SpringRunner.class)
class EnlightenmentDemoApplicationTests {

    @Autowired
    CryptoService service;

    public static void main(String[] args) {
        try {
            for (byte b : keyGen()) {
                System.out.println(b);
            }
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

    }

    @Test
    void contextLoads() throws Exception {
    }


}
