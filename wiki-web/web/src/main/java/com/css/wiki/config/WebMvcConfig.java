//package com.css.wiki.config;
//
//
//import com.css.wiki.interceptor.LogInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.annotation.Resource;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Resource
//    LogInterceptor logInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(logInterceptor)
//                .addPathPatterns("/**");
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }
//}
