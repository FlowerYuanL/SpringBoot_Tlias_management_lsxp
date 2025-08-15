package com.lsxp.interceptor;

import com.lsxp.utils.JWTutils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/*
* 令牌校验的拦截器
* */

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //判断请求是否包含login字段
//        if(request.getRequestURI().contains("/login")){
//            //如果包含login字段，直接放行
//            log.info("正在登录中...");
//            return true;
//        }
        //获取请求头中的token
        String token = request.getHeader("token");
        log.info("获取token为:{}",token);
        //如果不包含，判断其是否含有token
        if(token == null || token.isEmpty()){
            //如果没有token，响应401
            log.info("登录信息已经失效，请重新登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //如果有token，解析token
        try {
            JWTutils.parseToken(token);
        } catch (Exception e) {
            //如果解析失败，响应401
            log.info("登录信息已经失效，请重新登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //如果解析成功，放行
        log.info("身份信息正确，放行");
        return true;
    }
}
