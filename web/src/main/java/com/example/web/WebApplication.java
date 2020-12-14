package com.example.web;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.oas.annotations.EnableOpenApi;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan({"com.example"})
@EnableOpenApi
@EnableCaching
@EnableAsync
@EnableRabbit
@MapperScan("com.example.business.mapper")
@org.mybatis.spring.annotation.MapperScan("com.example.core.utils.tk.mapper")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
