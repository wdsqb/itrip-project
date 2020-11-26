package com.hzl.itripsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 信息搜索启动类
 * @date : 2020-11-23 13:57
 */
@EnableDiscoveryClient
@SpringBootApplication
public class itripSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(itripSearchApplication.class, args);
    }
}
