package com.lsxp.interceptor;

import com.lsxp.pojo.UserContextHoler;
import com.lsxp.pojo.UserDTO;
import com.lsxp.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/*
* 令牌校验的拦截器
* */

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    //注入JWT工具类
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取标注你的请求头Authorization
        String Authorization = request.getHeader("token");
        log.info("Authorization: {}", Authorization);
        //校验Bearer前缀
        if (!StringUtils.hasText(Authorization) || !Authorization.startsWith("Bearer ")) {
            log.error("授权信息为空或非法!");
            throw new SecurityException("授权信息为空或非法");
        }
        //获取token字符串
        String token = Authorization.substring(7);
        //调用JWT工具类，并将异常直接抛出，不需要手动设置401
        Claims claims = jwtUtils.parseToken(token);

        //获取用户的UserId，用于日志存储
        String userId = claims.getSubject();
        String userName = claims.get("userName",String.class);
        //将信息封装进UserDTO中
        UserDTO userDTO = new UserDTO(Integer.parseInt(userId),userName);
        //将信息存储到ThreadLocal中
        UserContextHoler.setUser(userDTO);
        //放行
        log.info("验证通过，当前操作人用户名:{}",userName);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHoler.clear();
    }
}
