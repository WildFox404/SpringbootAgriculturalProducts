package com.cow.spring_vue.config;


import com.cow.spring_vue.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login","/user/register");
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 假设您的Vue.js应用运行在http://localhost:8080上
        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080") // 替换为实际的Vue应用地址
                .allowedOrigins("http://localhost:8080") // 替换为实际的Vue应用地址
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的方法
                .maxAge(3600) // 预检请求的缓存时间
                .allowedHeaders("*"); // 允许所有头部，根据需要调整
    }
}