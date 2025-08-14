package com.lsxp.exception.entity;


/*
* 自定义异常处理器
* 班级有学生异常
* */
public class ClazzHasStudentsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClazzHasStudentsException(String message) {
        super(message);
    }

}
