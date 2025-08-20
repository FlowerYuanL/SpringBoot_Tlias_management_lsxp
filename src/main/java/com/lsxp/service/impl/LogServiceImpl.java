package com.lsxp.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsxp.mapper.LogMapper;
import com.lsxp.pojo.LogQueryParam;
import com.lsxp.pojo.OperateLog;
import com.lsxp.pojo.PageResult;
import com.lsxp.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LogServiceImpl implements LogService {

    //注入LogMapper
    @Autowired
    private LogMapper logMapper;

    @Override
    public PageResult<OperateLog> pageQuery(LogQueryParam logQueryParam) {
        PageHelper.startPage(logQueryParam.getPage(),logQueryParam.getPageSize());
        List<OperateLog> operateLogs = logMapper.pageQuery(logQueryParam);
        Page<OperateLog> page = (Page<OperateLog>) operateLogs;
        return new PageResult<>(page.getTotal(),page.getResult());
    }
}
