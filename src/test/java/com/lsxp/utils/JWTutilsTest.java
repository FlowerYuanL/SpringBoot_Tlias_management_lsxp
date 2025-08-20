package com.lsxp.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


class JWTutilsTest {


    @Test
    public void parseToken() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","zhangsan");
        String string = JWTutils.generateToken(map);
        Claims claims = JWTutils.parseToken(string);
        System.out.println(claims);
    }
}