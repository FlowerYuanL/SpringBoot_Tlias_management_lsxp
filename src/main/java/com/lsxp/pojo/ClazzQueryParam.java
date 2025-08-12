package com.lsxp.pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClazzQueryParam {

    /*班级管理页面的模糊分页查询*/
    private String name; //班级名称
    private LocalDate begin; //范围匹配的开始时间
    private LocalDate end; //范围匹配的结束时间
    private Integer page = 1; //分页查询的页码，默认为1
    private Integer pageSize = 10; //分页查询的煤业记录数，如果未指定，默认为10

}
