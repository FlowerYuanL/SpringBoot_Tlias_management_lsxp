package com.lsxp.service;

import com.lsxp.pojo.LogQueryParam;
import com.lsxp.pojo.OperateLog;
import com.lsxp.pojo.PageResult;

public interface LogService {
    public PageResult<OperateLog> pageQuery(LogQueryParam logQueryParam);
}
