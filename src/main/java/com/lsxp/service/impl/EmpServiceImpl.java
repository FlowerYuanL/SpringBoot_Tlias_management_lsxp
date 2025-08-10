package com.lsxp.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsxp.mapper.EmpExprMapper;
import com.lsxp.mapper.EmpMapper;
import com.lsxp.pojo.Emp;
import com.lsxp.pojo.EmpExpr;
import com.lsxp.pojo.EmpQueryParam;
import com.lsxp.pojo.PageResult;
import com.lsxp.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

//    @Override
//    public PageResult<Emp> queryAll(Integer page, Integer pageSize) {
//
//        //1.获取总共的人数
//        Long total = empMapper.count();
//
//        //2.查询所有的员工
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.findAll(start, pageSize);
//
//        //3.将获取的总人数和员工封装进PageResult
//        PageResult<Emp> pageResult = new PageResult<>(total,rows);
//
//        //4.返回结果
//        return pageResult;
//    }


    /**
     * 查询所有的员工列表，并获取总记录数
     * 通过pageHelper实现
     * @param page
     * @param pageSize
     * @return pageResult
     * 注意事项：
     *      1.在对应的Mapper中的SQL语句的结尾不能加分号
     *      2.pageHelper值会对仅仅能对紧跟在其后的第一行个查询语句的第一个查询语句进行分页处理
     */
    public PageResult<Emp> queryAll(EmpQueryParam empQueryParam) {
        log.info("EmpServiceImpl.queryAll()");
        //1.配置参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.调用Mapper方法
        List<Emp> emps = empMapper.findAll(empQueryParam);
        log.debug("List of emps: {}", emps);

        //3.解析并封装结果
        Page<Emp> p = (Page<Emp>) emps;
        PageResult<Emp> pageResult = new PageResult<>(p.getTotal(),p.getResult());
        //4.返回结果
        return pageResult;
    }

    @Override
    public void save(Emp emp) {
        //1. 将用户信息保存到用户数据表，并注意返回用户新增信息的id
        //   在此之前先设置好创建时间和更新时间
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        log.info("EmpServiceImpl.save(): {}", emp);
        //2.判断用户经历信息是否存在
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //3.如果用户经历信息存在，为用户经历表中的用户id赋上述刚得到的值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            //4.将用户经历信息保存到对应的用户经历信息表
            empExprMapper.insertBatch(exprList);
        }
    }
}
