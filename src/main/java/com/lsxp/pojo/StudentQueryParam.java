package com.lsxp.pojo;


import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentQueryParam {

    /*学生管理页面的模糊分页查询*/
    private String name; //学员姓名
    private Integer degree; //学历
    private Integer clazzId; //班级Id
    private Integer page = 1; //分页查询的页码，默认为1
    private Integer pageSize = 10; //分页查询的煤业记录数，如果未指定，默认为10

}
