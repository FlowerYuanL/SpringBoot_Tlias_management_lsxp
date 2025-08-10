package com.lsxp.service.impl;

import com.lsxp.mapper.DeptMapper;
import com.lsxp.pojo.Dept;
import com.lsxp.pojo.Result;
import com.lsxp.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> queryAll() {
        return deptMapper.queryAll();
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void save(Dept dept) {
        // 设置更新和创建时间
        dept.setUpdateTime(LocalDateTime.now());
        dept.setCreateTime(LocalDateTime.now());
        deptMapper.save(dept);
    }

    @Override
    public Dept findByID(Integer id) {
        return deptMapper.findByID(id);
    }

    @Override
    public void modifyById(Dept dept) {
        deptMapper.modifyById(dept);
    }
}
