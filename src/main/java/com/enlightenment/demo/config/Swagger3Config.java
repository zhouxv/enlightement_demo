package com.enlightenment.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;


@Configuration
public class Swagger3Config {
    @Bean
    public Docket sellerApi() {

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("光启项目Springboot后台接口文档")
                        .build())
                .select()
                .apis(basePackage("com.enlightenment.demo.controller.seller"))
                .paths(PathSelectors.any())
                .build().groupName("seller");
    }

    @Bean
    public Docket buyerApi() {

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("光启项目Springboot后台接口文档")
                        .build())
                .select()
                .apis(basePackage("com.enlightenment.demo.controller.buyer"))
                .paths(PathSelectors.any())
                .build().groupName("buyer");
    }

    @Bean
    public Docket userApi() {

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("光启项目Springboot后台接口文档")
                        .build())
                .select()
                .apis(basePackage("com.enlightenment.demo.controller.user"))
                .paths(PathSelectors.any())
                .build().groupName("user");
    }

}