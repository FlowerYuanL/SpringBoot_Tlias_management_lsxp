package com.lsxp.pojo;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {

    private Integer page = 1;//页码，默认为1
    private Integer pageSize = 10;//每页包含的信息数量，默认为10
    private String name;//模糊搜索的姓名
    private Integer gender;//性别，1男，2女
    private LocalDate begin;//范围匹配的开始时间
    private LocalDate end;//范围匹配的结束时间
}
