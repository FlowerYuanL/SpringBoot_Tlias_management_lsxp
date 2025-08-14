package com.lsxp.exception.entity;

public class ClazzEndException extends RuntimeException {

    //版本号
    private static final long serialVersionUID = 1L;

    //给出message信息
    public ClazzEndException(String message) {
        super(message);
    }
}