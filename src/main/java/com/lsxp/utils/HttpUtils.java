package com.lsxp.utils;


import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Component
public class HttpUtils {

    public Integer getUserID() throws Exception {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(requestAttributes == null){
            //如果不是web请求，直接返回
            return null;
        }
        HttpServletRequest request = requestAttributes.getRequest();

        //从请求头中获取JWT
        String token = request.getHeader("token");
        if(token == null){
            return null;
        }
        Claims claims = JWTutils.parseToken(token);
        return (Integer)claims.get("id");
    }

}
