package com.lsxp.controller;


import com.lsxp.pojo.Clazz;
import com.lsxp.pojo.ClazzQueryParam;
import com.lsxp.pojo.PageResult;
import com.lsxp.pojo.Result;
import com.lsxp.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    //注入服务层
    @Autowired
    private ClazzService clazzService;

    /*
    * 班级信息查询，支持分页处理和模糊搜索
    * */
    @GetMapping
    public Result pageClazzs(ClazzQueryParam clazzQueryParam) {
        log.info("班级信息搜索参数:{}", clazzQueryParam);
        PageResult<Clazz> clazzPageResult = clazzService.queryPage(clazzQueryParam);
        return Result.success(clazzPageResult);
    }
}
