package com.lsxp.controller;


import com.lsxp.pojo.GenderOption;
import com.lsxp.pojo.JobOption;
import com.lsxp.pojo.Result;
import com.lsxp.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
