package com.enlightenment.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@MapperScan("com.enlightenment.demo.mapper")
public class EnlightenmentDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnlightenmentDemoApplication.class, args);
    }
}
