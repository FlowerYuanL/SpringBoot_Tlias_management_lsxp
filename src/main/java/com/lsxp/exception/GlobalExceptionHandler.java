package com.lsxp.exception;


import com.lsxp.exception.entity.ClazzEndException;
import com.lsxp.exception.entity.ClazzHasStudentsException;
import com.lsxp.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result ExceptionHandler(Exception e) {
        log.error("全局异常!(异常未录入!):{}",e.getMessage(),e);
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
    public Result NoResourceFoundExceptionHandler(NoResourceFoundException e) {
        String message = e.getMessage();
        log.error("{}暂时未开发",message,e);
        return Result.error("出错啦，请联系管理员"+message);
    }

    /*
    * 绑定异常
    * */
    @ExceptionHandler(value = BindingException.class)
    public Result BindingExceptionHandler(BindingException e) {
        String message = e.getMessage();

        log.error("MyBatis映射出现问题，无法找到映射文件:{}",message);
        return Result.error("出错啦，请联系管理员解决问题~");
    }

    /*
    * 班级有学生异常——自定义异常
    * */
    @ExceptionHandler(value = ClazzHasStudentsException.class)
    public Result ClazzHasStudentsExceptionHandler(ClazzHasStudentsException e) {
        String message = e.getMessage();
        log.error("班级仍然存在学生，无法删除！！");
        return Result.error(message);
    }

    /*
    * 班级已经结课异常——自定义异常
    * */
    @ExceptionHandler(value = ClazzEndException.class)
    public Result ClazzEndExceptionHandler(ClazzEndException e) {
        String message = e.getMessage();
        log.error("班级已经结束，请选择正在进行的班级!!!");
        return Result.error(message);
    }

    /*
    * 空指针异常
    * */
    @ExceptionHandler(value = NullPointerException.class)
    public Result NullPointerExceptionHandler(NullPointerException e) {
        String message = e.getMessage();
        log.error("内容为空!!!");
        return Result.error(message);
    }

    /*
    * 登录信息验证异常
    * */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = SecurityException.class)
    public Result SecurityExceptionHandler(SecurityException e) {
        log.error("安全校验异常:{}",e.getMessage());
        return Result.error(e.getMessage());
    }
}
