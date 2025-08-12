package com.lsxp.exception;


import com.lsxp.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result ExceptionHandler(Exception e) {
        log.error("程序出错啦",e.getMessage(),e);
        return Result.error("出错啦，请联系管理员~");
    }


    @ExceptionHandler(value = DuplicateKeyException.class)
    public Result DuplicateKeyExceptionHandler(DuplicateKeyException e) {
        log.error("程序出现问题！{}",e.getMessage(),e);
        String message = e.getMessage();
        int index = message.lastIndexOf(":");
        String errorMessage = message.substring(index + 1).split(";")[1];

        return Result.error("出错了，请联系管理员："+errorMessage);
    }



}
