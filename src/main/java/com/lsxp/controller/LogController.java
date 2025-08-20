package com.lsxp.controller;

import com.lsxp.pojo.LogQueryParam;
import com.lsxp.pojo.OperateLog;
import com.lsxp.pojo.PageResult;
import com.lsxp.pojo.Result;
import com.lsxp.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/log")
public class LogController {

    //注入LogService接口
    @Autowired
    private LogService logService;

    @GetMapping("/page")
    public Result pageLog(LogQueryParam logQueryParam) {
        log.info("日志分页查询参数:{}",logQueryParam);
        PageResult<OperateLog> pageResult = logService.pageQuery(logQueryParam);
        return Result.success(pageResult);
    }

}
