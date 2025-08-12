package com.lsxp.exception;


import com.lsxp.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result ExceptionHandler(Exception e) {
        log.error("全局异常!(异常未录入!)");
        return Result.error("出错啦，请联系管理员~");
    }


    /*
    * 字段重复异常
    * */
    @ExceptionHandler(value = DuplicateKeyException.class)
    public Result DuplicateKeyExceptionHandler(DuplicateKeyException e) {
        log.error("字段重复异常");
        String message = e.getMessage();
        int index = message.lastIndexOf(":");
        String errorMessage = message.substring(index + 1).split(";")[1];

        return Result.error("出错了，请联系管理员："+errorMessage);
    }

    /*
    * 静态资源未找到异常
    * */
    @ExceptionHandler(value = NoResourceFoundException.class)
    public Result NoResourceFoundExceptionhandler(NoResourceFoundException e) {
        String message = e.getMessage();
        int index = message.lastIndexOf("static resource");
        String errorMessage = message.substring(index + 13).split(" ")[1];
        log.error("{}暂时未开发",errorMessage);
        return Result.error("出错啦，请联系管理员"+errorMessage);
    }


}
