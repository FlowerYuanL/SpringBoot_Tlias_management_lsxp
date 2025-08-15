package com.lsxp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.*;

public class JwtTest {


    /*
    * 成成JWT令牌
    * */
    @Test
    public void testGenerateJwt(){

        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","test");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString("lsxp".getBytes()))
                .addClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 1000))
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidGVzdCIsImlkIjoxLCJleHAiOjE3NTUxNTk5NzR9.GemV5_81214bOWwh-N8cTgszEcQqFVo1QQ_4gTt9SqQ";
        Claims claims = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString("lsxp".getBytes()))
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }


    @Test
    public void testBase64(){
        String test = "lsxphbxz";

        System.out.println(Arrays.toString(test.getBytes()));

        System.out.println(Base64.getEncoder().encodeToString(test.getBytes()));

        System.out.println(Arrays.toString(Base64.getDecoder().decode(Base64.getEncoder().encodeToString(test.getBytes()))));
    }
}
