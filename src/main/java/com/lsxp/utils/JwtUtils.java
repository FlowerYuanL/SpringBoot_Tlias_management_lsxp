package com.lsxp.utils;


import com.lsxp.config.properties.JwtProperties;

import io.jsonwebtoken.*;

import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;



@Slf4j
@Component
public class JwtUtils {

    @Autowired
    private JwtProperties jwtProperties;
    private Key key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtProperties.getSecretKey());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 生成Token
     * @param claims 业务信息
     * @param UserId 用户的唯一标识（用户sub）
     * @return String JWT字符串
     */
    public String generateToken(Map<String, Object> claims,String UserId) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(UserId)
                .setAudience(jwtProperties.getAudience())
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+jwtProperties.getExpirationTime()))
                .setId(UUID.randomUUID().toString())
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .requireAudience(jwtProperties.getAudience())
                    .requireIssuer(jwtProperties.getIssuer())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            log.error("无效的JWT签名: {}", e.getMessage());
            throw new SecurityException("无效的JWT签名");
        } catch (MalformedJwtException e) {
            log.error("无效的JWT令牌: {}", e.getMessage());
            throw new SecurityException("无效的JWT令牌");
        } catch (ExpiredJwtException e) {
            log.error("JWT令牌已过期: {}", e.getMessage());
            throw new SecurityException("JWT令牌已过期");
        } catch (UnsupportedJwtException e) {
            log.error("不支持的JWT令牌: {}", e.getMessage());
            throw new SecurityException("不支持的JWT令牌");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims字符串为空: {}", e.getMessage());
            throw new SecurityException("JWT claims字符串为空");
        }
    }
}
