package com.lsxp.pojo;


/*
* 班级表
* */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.lang.String;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {
    private Integer id; //ID
    private String name; //班级名称
    private String room; //班级教室
    private LocalDate beginDate; //开课时间
    private LocalDate endDate; //结课时间
    private Integer masterId; //班主任
    private Integer subject; //学科
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间

    /*
     * 注意此处在传递班主任名称时需要定义别名为masterName或者master_name
     * */
    private String masterName; //班主任姓名

    private String status; //班级状态 - 未开班 , 在读 , 已结课

    /*
    * 获取班级状态
    * */
    public String getStatusByTimes() {
        return this.beginDate.isAfter(LocalDate.now()) ? "未开班" : (!this.endDate.isBefore(LocalDate.now()) ? "已开班" : "已结课");
    }
}
