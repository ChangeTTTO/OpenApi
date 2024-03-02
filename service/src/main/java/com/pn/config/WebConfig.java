//package com.pn.config;
//
//import jakarta.annotation.Resource;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    // 在这里添加你的 WebMVC 配置
//    @Resource
//    private LoginInterceptor loginInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor)
//                .excludePathPatterns("/**/login/**", "/webjars/**", "/doc.html", "/v3/**", "/swagger-resources/**")
//                .addPathPatterns("/**");
//    }
//
//
//
//}