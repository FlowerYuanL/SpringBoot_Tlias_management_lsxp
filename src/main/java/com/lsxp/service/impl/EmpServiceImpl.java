package com.lsxp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsxp.mapper.EmpExprMapper;
import com.lsxp.mapper.EmpMapper;
import com.lsxp.pojo.*;
import com.lsxp.service.EmpLogService;
import com.lsxp.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    //注入日志操作的service层
    @Autowired
    private EmpLogService empLogService;

    //借助ObjectMapper实现JSON序列化
    @Autowired
    private ObjectMapper mapper;

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

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        EmpLog empLog = new EmpLog();
        try {
            //1. 将用户信息保存到用户数据表，并注意返回用户新增信息的id
            //   在此之前先设置好创建时间和更新时间
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            log.info("保存员工基本信息:{}", emp);
            //2.判断用户经历信息是否为空
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                //3.如果用户经历信息存在，为用户经历表中的用户id赋上述刚得到的值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                //4.将用户经历信息保存到对应的用户经历信息表
                empExprMapper.insertBatch(exprList);
                log.info("保存员工工作经历信息:{}",exprList);
            }
            empLog.setResult("SUCCESS");
        }catch (Exception e){
            log.error("保存用户信息出错:{}",e.getMessage(),e);
            throw e;
        }
        finally {
            //将上述操作保存到记录到数据库的日志中去
            try {
                empLog.setMsg(mapper.writeValueAsString(emp));
            } catch (JsonProcessingException e) {
                empLog.setMsg(e.getMessage());
                log.error("员工信息序列化失败...{}",e.getMessage(),e);
            }
            empLogService.saveEmpLog(empLog);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void delete(List<Integer> ids) {

        // 1.在员工表中批量删除——delete by ids
        empMapper.deleteBatch(ids);

        // 2.在员工经历表中批量删除——delete by empIds
        empExprMapper.deleteBatch(ids);
    }

    @Override
    public Emp queryId(Integer id) {
        //1.根据id查询用户详细信息和用户经历信息
        Emp emp = empMapper.findById(id);
        return emp;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update(Emp emp) {
        //设置该表的更新时间
        emp.setUpdateTime(LocalDateTime.now());
        //调用方法更新员工信息
        empMapper.update(emp);
        //获取员工经历信息
        List<EmpExpr> exprList = emp.getExprList();
        log.info("获取员工经历信息:{}", exprList);
        //删除原来的员工经历信息——借助Arrays.asList使用批量删除
        empExprMapper.deleteBatch(Arrays.asList(emp.getId()));
        //判空
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> lsxpQueryAll() {
        List<Emp> empList = empMapper.lsxpQueryAll();
        return  empList;
    }
}
