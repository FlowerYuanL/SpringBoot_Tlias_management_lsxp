package com.lsxp.controller;


import com.lsxp.pojo.Emp;
import com.lsxp.pojo.EmpQueryParam;
import com.lsxp.pojo.PageResult;
import com.lsxp.pojo.Result;
import com.lsxp.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    /*
    * GetMapping ->Select
    * DeleteMapping ->Delete
    * PostMapping ->Insert
    * PutMapping ->Update
    * */

    @Autowired
    private EmpService empService;

     /*
     * 借助PageHelper实现员工信息的分页查询
     * */
    @GetMapping
    public Result queryAllPage(EmpQueryParam empQueryParam){
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

    /*
    * 删除员工
    * */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("获取请求参数:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /*
    * 根据员工id查询员工信息
    * */
    @GetMapping("/{id}")
    public Result queryId(@PathVariable Integer id){
        log.info("获取请求参数:{}",id);
        Emp emp = empService.queryId(id);
        return Result.success(emp);
    }

    /*
    * 编辑员工信息
    * */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("获取请求参数:{}",emp);
        empService.update(emp);
        return Result.success();
    }

    /*
    * 查询全部员工信息——为了实现课程表中选择班主任的部分
    * */
    @GetMapping("/list")
    public Result queryAll(){
        log.info("为班级表查询全部员工信息");
        List<Emp> empList = empService.lsxpQueryAll();
        return Result.success(empList);
    }

}
