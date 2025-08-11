package com.lsxp.service.impl;

import com.lsxp.mapper.EmpLogMapper;
import com.lsxp.pojo.EmpLog;
import com.lsxp.service.EmpLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveEmpLog(EmpLog empLog) {
        empLog.setDate(LocalDateTime.now());
        log.info("保存日志到数据库:{}",empLog);
        empLogMapper.insert(empLog);
    }
}
