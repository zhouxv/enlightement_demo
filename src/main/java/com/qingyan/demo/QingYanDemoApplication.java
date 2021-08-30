package com.qingyan.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@MapperScan("com.qingyan.demo.mapper")
public class QingYanDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(QingYanDemoApplication.class, args);
    }
}
