package com.lsxp.controller;


import com.lsxp.pojo.*;
import com.lsxp.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /*
    * 统计员工职位信息，人数
    * */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("正在统计员工职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /*
    * 统计员工性别信息，人数
    * */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("正在统计员工性别信息");
        List<GenderOption> genderOptions = reportService.getEmpGenderData();
        return Result.success(genderOptions);
    }

    /*
    * 班级人数统计——统计每一个班级的人数
    * */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("正在统计班级人数信息");
        StudentOption studentOption = reportService.getStudentCountData();
        return Result.success(studentOption);
    }

    /*
    * 学员学历信息统计——统计学员的学历信息
    * */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("正在统计学员学历信息");
        List<DegreeOption> degreeOptionList = reportService.getStudentDegreeData();
        return Result.success(degreeOptionList);
    }
}
