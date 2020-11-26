package com.hzl.itripbiz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description :
 * @date : 2020-11-23 14:12
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "com.hzl.mapper")
public class itripBizApplication {
    public static void main(String[] args) {
        SpringApplication.run(itripBizApplication.class, args);
    }
}
