package com.lsxp.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsxp.mapper.ClazzMapper;
import com.lsxp.pojo.Clazz;
import com.lsxp.pojo.ClazzQueryParam;
import com.lsxp.pojo.PageResult;
import com.lsxp.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
public class ClazzServiceImpl implements ClazzService {

    // 注入ClazzMapper接口
    @Autowired
    private ClazzMapper clazzMapper;


    @Override
    public PageResult<Clazz> queryPage(ClazzQueryParam clazzQueryParam) {
        //1. 配置PageHelper参数，需要传入page和pageSize——拦截接下来的第一个MyBatis查询
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        //2. 调用ClazzMapper接口中的方法查询班级的全部信息
        List<Clazz> clazzList = clazzMapper.findAll(clazzQueryParam);

        clazzList.forEach(clazz -> {
            //获取当前时间，根据时间设置clazzList中的返回值
            clazz.setStatus(clazz.getBeginDate().isAfter(LocalDate.now())? "未开班": (!clazz.getEndDate().isBefore(LocalDate.now())?"已开班":"已结课"));
        });

        //3. 将返回的信息强转为Page<Clazz>类型
        Page<Clazz> p =  (Page<Clazz>) clazzList;
        log.info("获取班级的全部信息:{}",p.getResult());
        //4. 将信息封装仅PageResult
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }
}
