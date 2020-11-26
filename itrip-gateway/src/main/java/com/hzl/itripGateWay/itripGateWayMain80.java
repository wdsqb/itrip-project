package com.hzl.itripGateWay;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 网关启动类
 * @date : 2020-11-20 15:28
 */
@EnableDiscoveryClient
@SpringCloudApplication
public class itripGateWayMain80 {
    public static void main(String[] args) {
        SpringApplication.run(itripGateWayMain80.class,args );
    }
}
