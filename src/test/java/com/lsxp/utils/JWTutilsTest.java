package com.lsxp.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class JWTutilsTest {

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    public void testJWTutils(){
        Map<String,Object> map = new HashMap<>();
        map.put("username","zhangsan");
        String Authorization = jwtUtils.generateToken(map, "1");
        Claims claims = jwtUtils.parseToken(Authorization);
        Object username = claims.get("username");
        System.out.println(username.toString());
    }
}