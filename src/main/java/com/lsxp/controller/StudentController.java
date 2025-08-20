package com.lsxp.controller;


import com.lsxp.annotation.LogAnnotation;
import com.lsxp.pojo.PageResult;
import com.lsxp.pojo.Result;
import com.lsxp.pojo.Student;
import com.lsxp.pojo.StudentQueryParam;
import com.lsxp.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    /*
     * GetMapping ->Select
     * DeleteMapping ->Delete
     * PostMapping ->Insert
     * PutMapping ->Update
     * */


    //注入StudentService
    @Autowired
    private StudentService studentService;

    /*
    * 学院信息查询，支持分页处理和模糊搜索
    *
    * */
    @GetMapping
    public Result pageStudents(StudentQueryParam studentQueryParam) {
        log.info("学生管理页面分页查询，接收查询参数:{}", studentQueryParam);
        PageResult<Student> studentList = studentService.pageStudents(studentQueryParam);
        return Result.success(studentList);
    }

    /*
    * 插入操作
    * */
    @PostMapping
    @LogAnnotation
    public Result saveStudent(@RequestBody Student student) {
        log.info("保存学生信息，从前端接收数据:{}", student);
        studentService.saveStudent(student);
        return Result.success();
    }

    /*
    * 根据Id查询学员信息
    * 通过注解@PathVariable接收传递来的路径参数
    * */
    @GetMapping("/{id}")
    public Result getStudentById(@PathVariable Integer id) {
        log.info("通过Id查询学员信息，接收前端数据:{}",id);
        Student student = studentService.getStudentById(id);
        return Result.success(student);
    }

    /*
    * 修改学生信息
    * 通过注解@RequestBody接收前端传来的请求体参数
    * */
    @PutMapping
    @LogAnnotation
    public Result updateStudent(@RequestBody Student student) {
        log.info("修改学员信息，接收前端传递来的请求体参数:{}", student);
        studentService.updateStudent(student);
        return Result.success();
    }

    /*
    * 根据id数组批量删除学员信息
    * 通过注解@PathVariable接收前端传递来的id数组
    * */
    @DeleteMapping("/{ids}")
    @LogAnnotation
    public Result deleteStudents(@PathVariable Integer[] ids) {
        log.info("删除学员信息，传来的数组为:{}",ids);
        studentService.deleteStudents(ids);
        return Result.success();
    }


    /*
    * 违纪处理
    * 学生违纪->违纪分数增加score->违纪次数+1
    * */
    @PutMapping("/violation/{id}/{score}")
    @LogAnnotation
    public Result updateStudentViolation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("学员id:{}，违纪增加的分数:{}",id,score);
        studentService.updateStudentViolation(id,score);
        return Result.success();
    }
}



