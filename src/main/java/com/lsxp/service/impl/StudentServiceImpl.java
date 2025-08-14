package com.lsxp.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lsxp.mapper.StudentMapper;
import com.lsxp.pojo.PageResult;
import com.lsxp.pojo.Student;
import com.lsxp.pojo.StudentQueryParam;
import com.lsxp.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    //注入StudentMapper接口
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> pageStudents(StudentQueryParam studentQueryParam) {
        log.info("到达Service层");
        //配置pageHelper参数
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        //调用StudentMapper调用查询函数
        List<Student> studentList = studentMapper.findWithClazz(studentQueryParam);
        //将返回值强转为Page<Student>类型
        Page<Student> p = (Page<Student>) studentList;
        //调用Page的方发getTotal()和getResult()将结果封装进PageResult<Student>中
        PageResult<Student> pageResult = new PageResult<>(p.getTotal(),p.getResult());
        //返回结果
        return pageResult;
    }

    @Override
    public void saveStudent(Student student) {
        //1.设置创建时间和更新时间
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        log.info("更新信息:{}",student);
        //2.调用函数将信息插入
        studentMapper.saveStudent(student);
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.getStudentById(id);
    }

    @Override
    public void updateStudent(Student student) {
        //1.设置更新时间的值为当前时间
        student.setUpdateTime(LocalDateTime.now());
        log.info("更新信息:{}",student);
        //2.调用函数将信息更新
        studentMapper.updateStudent(student);
    }

    @Override
    public void deleteStudents(Integer[] ids) {
        studentMapper.deleteStudents(ids);
    }

    @Override
    public void updateStudentViolation(Integer id, Integer score) {
        studentMapper.updateStudentViolation(id,score);
    }
}
