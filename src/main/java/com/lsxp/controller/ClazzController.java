package com.lsxp.controller;


import com.google.errorprone.annotations.concurrent.LazyInit;
import com.lsxp.annotation.LogAnnotation;
import com.lsxp.pojo.Clazz;
import com.lsxp.pojo.ClazzQueryParam;
import com.lsxp.pojo.PageResult;
import com.lsxp.pojo.Result;
import com.lsxp.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@Lazy
@RequestMapping("/clazzs")
public class ClazzController {

    /*
     * GetMapping ->Select
     * DeleteMapping ->Delete
     * PostMapping ->Insert
     * PutMapping ->Update
     * */

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

    /*
    * 删除班级——根据id删除班级信息
    * 接收路径参数，通过注解@PathVaraible标记参数
    * 如果该班级下关联的有学生，不允许删除，并提示错误信息"对不起, 该班级下有学生, 不能直接删除"
    * */
    @DeleteMapping("/{id}")
    @LogAnnotation
    public Result deleteClazz(@PathVariable Integer id) {
        log.info("根据id删除班级信息:{}",id);
        clazzService.deleteClazz(id);
        return Result.success();
    }

    /*
    * 新增班级
    * 接收json格式的参数，通过注解@RequestBody标记参数
    * */
    @PostMapping
    @LogAnnotation
    public Result saveClazz(@RequestBody Clazz clazz) {
        log.info("新增班级于{},新增班级信息:{}", LocalDateTime.now(), clazz);
        clazzService.saveClazz(clazz);
        return Result.success();
    }


    /*
    * 信息回显——根据id查询班级
    * 接收简单参数，可以通过注解@PathVariable标记参数
    * */
    @GetMapping("/{id}")
    public Result getClazzById(@PathVariable Integer id) {
        log.info("根据id查询班级:{}",id);
        Clazz clazz = clazzService.getClazzById(id);
        return Result.success(clazz);
    }

    /*
    * 编辑班级信息
    * 接收json格式的参数，通过注解@RequestBody标记参数
    * */
    @PutMapping
    @LogAnnotation
    public Result updateClazz(@RequestBody Clazz clazz) {
        log.info("编辑班级信息，修改后的班级信息为:{}",clazz);
        clazzService.updateClazz(clazz);
        return Result.success();
    }

    /*
    * 查询所有班级——在新增学员时，用来展示所有的班级信息
    * */
    @GetMapping("/list")
    public Result getAllClazz(){
        log.info("查询全体班级信息——用来展示所有的班级信息");
        List<Clazz> clazzList = clazzService.getAllClazz();
        return Result.success(clazzList);
    }
}
