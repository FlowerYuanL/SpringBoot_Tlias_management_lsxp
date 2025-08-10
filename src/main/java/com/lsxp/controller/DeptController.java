package com.lsxp.controller;

import com.lsxp.pojo.Dept;
import com.lsxp.pojo.Result;
import com.lsxp.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /*
    * 查询部门列表
    * */
    @GetMapping
    public Result list(){
        log.info("Controller:查询部门列表...");
        return Result.success(deptService.queryAll());
    }

    /*
    * 通过Id删除部门信息
    * */
    @DeleteMapping
    public Result deleteById(Integer id){
        log.debug("Controller:通过ID:{}删除部门信息",id);
        deptService.deleteById(id);
        return Result.success();
    }

    /*
    * 添加部门
    * */
    @PostMapping
    public Result save(@RequestBody Dept dept){
        String name = dept.getName();
        log.info("Controller:新增部门信息，部门名称为:{},新增时间为:{}",name, LocalDateTime.now());
        deptService.save(dept);
        return Result.success();
    }

    /*
    * 修改部门——查询回显（根据ID获取部门信息）
    * */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("Controller:通过ID为{}获取部门信息",id);
        Dept dept = deptService.findByID(id);
        return Result.success(dept);
    }

    /*
    * 修改部门——修改数据
    * */
    @PutMapping
    public Result updateByID(@RequestBody Dept dept){
        log.info("Controller:更改id为{}的部门名称为:{}",dept.getId(),dept.getName());
        deptService.modifyById(dept);
        return Result.success();
    }
}
