package com.lsxp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogQueryParam {

    private Integer page = 1;//页码

    private Integer pageSize = 10;//每页展示的记录数
}
