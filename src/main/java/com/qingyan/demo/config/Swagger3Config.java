package com.qingyan.demo.config;

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
    public Docket userApi() {

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("清雁项目Springboot后台接口文档")
                        .build())
                .select()
                .apis(basePackage("com.qingyan.demo.controller.user"))
                .paths(PathSelectors.any())
                .build().groupName("user");
    }

    @Bean
    public Docket goodsApi() {

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("清雁项目Springboot后台接口文档")
                        .build())
                .select()
                .apis(basePackage("com.qingyan.demo.controller.goods"))
                .paths(PathSelectors.any())
                .build().groupName("goods");
    }

    @Bean
    public Docket transactionApi() {

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("清雁项目Springboot后台接口文档")
                        .build())
                .select()
                .apis(basePackage("com.qingyan.demo.controller.transaction"))
                .paths(PathSelectors.any())
                .build().groupName("transaction");
    }

    @Bean
    public Docket aftermarketApi() {

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title("清雁项目Springboot后台接口文档")
                        .build())
                .select()
                .apis(basePackage("com.qingyan.demo.controller.aftermarket"))
                .paths(PathSelectors.any())
                .build().groupName("aftermarket");
    }

}