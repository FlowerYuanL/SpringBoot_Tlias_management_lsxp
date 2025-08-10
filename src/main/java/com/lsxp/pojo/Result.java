package com.lsxp.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Result {

    /*
    * code：1：成功 0：失败
    * msg：错误信息
    * data：数据
    * */
    private Integer code;
    private String msg;
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        result.data = data;
        return result;
    }

    public static Result error( String msg) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }

}
