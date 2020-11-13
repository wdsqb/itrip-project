package com.hzl.itripauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.hzl.itripauth.*"})
public class ItripAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItripAuthApplication.class, args);
    }

}
