package com.lsxp.config.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secretKey;   //密钥
    private Long expirationTime;    //过期时间
    private String issuer;  //签发者
    private String audience;    //受众
}
