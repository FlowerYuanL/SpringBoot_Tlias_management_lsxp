package com.lsxp.service;

import com.lsxp.pojo.Dept;
import com.lsxp.pojo.Result;

import java.util.List;

public interface DeptService {
    List<Dept> queryAll();

    void deleteById(Integer id);

    void save(Dept dept);

    Dept findByID(Integer id);

    void modifyById(Dept dept);
}
