package com.lsxp.filter;


import com.lsxp.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
* "/exampleFolder/*"->代表某一目录下全部文件
*               "/*"->代表全部的文件
* */

//@Component
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    //注入JWT的工具类
    private JwtUtils jwtUtils;

    public TokenFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将请求和响应均转换为Http格式的数据
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //判断请求是否包含login字段
        if(request.getRequestURI().contains("/login")){
            //如果包含login字段，直接放行
            log.info("正在登录中...");
            filterChain.doFilter(request,response);
            return;
        }
        //获取请求头中的token
        String token = request.getHeader("token");
        log.info("获取token为:{}",token);
        //如果不包含，判断其是否含有token
        if(token == null || token.isEmpty()){
            //如果没有token，响应401
            log.info("登录信息已经失效，请重新登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //如果有token，解析token
        try {
            jwtUtils.parseToken(token);
        } catch (Exception e) {
            //如果解析失败，响应401
            log.info("登录信息已经失效，请重新登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //如果解析成功，放行
        log.info("身份信息正确，放行");
        filterChain.doFilter(request,response);
    }
}
