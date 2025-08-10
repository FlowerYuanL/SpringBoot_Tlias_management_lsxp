package com.lsxp.service;

import com.lsxp.pojo.Emp;
import com.lsxp.pojo.EmpQueryParam;
import com.lsxp.pojo.PageResult;

public interface EmpService {
    PageResult<Emp> queryAll(EmpQueryParam empQueryParam);

    void save(Emp emp);
}
