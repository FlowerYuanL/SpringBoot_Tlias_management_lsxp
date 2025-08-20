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
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImRlbW8iLCJzdWIiOiIyIiwiYXVkIjoibXktYXBwLWNsaWVudHMiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvbG9naW4iLCJpYXQiOjE3NTU2ODQ1MjIsImV4cCI6MTc1NTcyNzcyMiwianRpIjoiMzY5YzlhODktZDk5ZC00YjRhLTg1ZmUtZDE0NTQ5Y2I5YmE4In0.LkSw50E2GCMmKxZakpwQwgi2vb-ZBGuH663Y-YjiGrc";
        String Authorization = jwtUtils.generateToken(map, "1");
        Claims claims = jwtUtils.parseToken(token);
        Object username = claims.get("username");
        System.out.println(username.toString());
    }
}