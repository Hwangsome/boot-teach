package com.bh.permission.config;

import com.bh.permission.interceptor.URLInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 将拦截器注入应用
 */
@Configuration
public class WebMvcConfg implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //权限拦截器
        registry.addInterceptor(urlInterceptor()).addPathPatterns("/**");
    }
    @Bean
    public URLInterceptor urlInterceptor(){
        return new URLInterceptor();
    }
}