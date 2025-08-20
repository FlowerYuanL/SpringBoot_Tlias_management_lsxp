package com.lsxp.config;

import com.lsxp.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }

    /**
     * 配置全局跨域解决方案
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 1. 使用 allowedOriginPatterns("*") 解决 allowCredentials(true) 与通配符冲突的问题
                .allowedOriginPatterns("*")
                // 2. 允许的方法列表，确保使用大写，并包含 "OPTIONS"
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 3. 允许携带凭证（如Cookie和Authorization头）
                .allowCredentials(true)
                // 4. 允许所有请求头
                .allowedHeaders("*")
                // 5. 预检请求的有效期，减少预检请求的频率
                .maxAge(3600);
    }
}
