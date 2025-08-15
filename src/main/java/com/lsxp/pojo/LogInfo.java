package com.lsxp.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogInfo {
    private Integer id;
    private String username;
    private String name;
    private String token;
}
