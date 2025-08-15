package com.lsxp.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;
import java.util.Map;





/*
* TODO 将实现方式改为Spring注入的方式
*      密钥，有效时间不能写死，建议保存在配置文件中
*      JWT0.9已经落伍，现在通常选择0.11
*      抛出异常可以结合全局异常处理进行优化
*
*
* */
public class JWTutils {

    private static final String SECRET_KEY = "lsxphbxz";
    private static final long EXPIRATION_TIME = 12*60*60*1000;

    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .compact();
    }

    public static Claims parseToken(String token) throws  Exception{
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }
}
