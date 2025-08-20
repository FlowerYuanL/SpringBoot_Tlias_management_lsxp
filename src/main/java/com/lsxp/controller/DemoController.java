package com.lsxp.controller;


import com.lsxp.annotation.LogAnnotation;
import com.lsxp.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {

    @GetMapping("demo")
    @LogAnnotation
    public Result demoAPI(){
        log.info("正在调用demo接口");
        return Result.success();
    }
}
