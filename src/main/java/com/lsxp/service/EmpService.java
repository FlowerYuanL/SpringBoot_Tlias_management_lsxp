package com.lsxp.service;

import com.lsxp.pojo.Emp;
import com.lsxp.pojo.EmpQueryParam;
import com.lsxp.pojo.LogInfo;
import com.lsxp.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult<Emp> queryAll(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp queryId(Integer id);

    void update(Emp emp);

    List<Emp> lsxpQueryAll();

    LogInfo login(Emp emp);
}
