package com.hzl.itripauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 前后端跨域配置
 * @date : 2020-11-12 11:54
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") .allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "HEAD", "OPTIONS")
                .maxAge(3600) .allowedHeaders("*");
    }
}
