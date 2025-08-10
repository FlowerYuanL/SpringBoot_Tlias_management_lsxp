package com.lsxp.controller;


import com.lsxp.pojo.Emp;
import com.lsxp.pojo.EmpQueryParam;
import com.lsxp.pojo.PageResult;
import com.lsxp.pojo.Result;
import com.lsxp.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

     /*
     * 借助PageHelper实现员工信息的分页查询
     * */
    @GetMapping
    public Result queryAll(EmpQueryParam empQueryParam){
        log.info("查询参数：{}",empQueryParam);
        PageResult<Emp> pageResult = empService.queryAll(empQueryParam);
        return Result.success(pageResult);
    }

    /*
    * 新增员工
    * */
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("获取请求参数:{}",emp);
        empService.save(emp);
        return Result.success();
    }

}
