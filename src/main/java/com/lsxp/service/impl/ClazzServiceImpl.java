package com.lsxp.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsxp.exception.entity.ClazzHasStudentsException;
import com.lsxp.mapper.ClazzMapper;
import com.lsxp.mapper.StudentMapper;
import com.lsxp.pojo.Clazz;
import com.lsxp.pojo.ClazzQueryParam;
import com.lsxp.pojo.PageResult;
import com.lsxp.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@Slf4j
@Service
public class ClazzServiceImpl implements ClazzService {

    // 注入ClazzMapper接口
    @Autowired
    private ClazzMapper clazzMapper;

    //注入StudentMapper接口
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public PageResult<Clazz> queryPage(ClazzQueryParam clazzQueryParam) {
        //1. 配置PageHelper参数，需要传入page和pageSize——拦截接下来的第一个MyBatis查询
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        //2. 调用ClazzMapper接口中的方法查询班级的全部信息
        List<Clazz> clazzList = clazzMapper.findAll(clazzQueryParam);
        //判断clazzList是否为空
        if(!CollectionUtils.isEmpty(clazzList)){
            clazzList.forEach(clazz -> {
                //获取当前时间，根据时间设置clazzList中的返回值
                clazz.setStatus(clazz.getStatusByTimes());
            });
        }
        //3. 将返回的信息强转为Page<Clazz>类型
        Page<Clazz> p =  (Page<Clazz>) clazzList;
        log.info("获取班级的全部信息:{}",p.getResult());
        //4. 将信息封装仅PageResult
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteClazz(Integer id) {
        //查询该班级下是否还有学生存在
        //调用StudentMapper接口查寻clazz_id = #{id}的数量
        Integer studentsInClazz = studentMapper.findByClazzId(id);
        //如果有，抛出异常
        //如果返回结果大于0，抛出异常
        if(studentsInClazz>0){
            log.info("该班级学生的数量为:{}",studentsInClazz);
            throw new ClazzHasStudentsException("对不起，该班级下有学生，不能直接删除");
        }
        //如果没有，调用函数删除班级信息
        clazzMapper.deleteById(id);
    }

    @Override
    public void saveClazz(Clazz clazz) {
        //需要为创建时间和更新时间赋值
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        log.info("最终插入字段:{}",clazz);
        clazzMapper.saveClazz(clazz);
    }

    @Override
    public Clazz getClazzById(Integer id) {
        Clazz clazz = clazzMapper.getClazzById(id);
        return clazz;
    }

    @Override
    public void updateClazz(Clazz clazz) {
        //需要为更新时间赋值
        clazz.setUpdateTime(LocalDateTime.now());
        log.info("最终更改字段为:{}",clazz);
        clazzMapper.updateClazz(clazz);
    }

    @Override
    public List<Clazz> getAllClazz() {
        return clazzMapper.getAllClazz();
    }
}
