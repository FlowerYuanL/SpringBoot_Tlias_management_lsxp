package com.lsxp.mapper;

import com.lsxp.pojo.Student;
import com.lsxp.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    Integer findByClazzId(Integer id);

    List<Student> findWithClazz(StudentQueryParam studentQueryParam);

    void saveStudent(Student student);

    Student getStudentById(Integer id);

    void updateStudent(Student student);

    void deleteStudents(Integer[] ids);

    void updateStudentViolation(Integer id, Integer score);
}
