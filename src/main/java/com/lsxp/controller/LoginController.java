package com.lsxp.controller;


import com.lsxp.pojo.Emp;
import com.lsxp.pojo.LogInfo;
import com.lsxp.pojo.Result;
import com.lsxp.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
* 登录Controller
* */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    /*
    * 登录功能，注意需要使用Post提交数据（更安全）
    * 使用注解@RequestBody接收前端传来的数据
    * */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登录信息——用户名：{},密码：{}",emp.getUsername(),emp.getPassword());
        LogInfo info = empService.login(emp);
        if(info!=null){
            return Result.success(info);
        }
        return Result.error("用户名或密码错误");
    }
}

