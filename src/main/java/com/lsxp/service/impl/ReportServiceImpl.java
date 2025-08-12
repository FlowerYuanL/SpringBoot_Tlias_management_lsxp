package com.lsxp.service.impl;

import com.lsxp.mapper.EmpMapper;
import com.lsxp.pojo.GenderOption;
import com.lsxp.pojo.JobOption;
import com.lsxp.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;


    @Override
    public JobOption getEmpJobData() {
        //1. 调用mapper接口，获取统计数据
        List<Map<String, Object>> listMap = empMapper.countJob();
        //2. 组装结果，返回
        JobOption jobOption = new JobOption();
        List<Object> positions = listMap.stream().map(dataMap -> dataMap.get("position")).collect(Collectors.toList());
        List<Object> nums = listMap.stream().map(dataMap -> dataMap.get("num")).collect(Collectors.toList());
        jobOption.setJobList(positions);
        jobOption.setDataList(nums);
        return jobOption;
    }

    @Override
    public List<GenderOption> getEmpGenderData() {
        return empMapper.countGender();
    }
}
