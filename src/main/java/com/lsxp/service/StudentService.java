package com.lsxp.service;

import com.lsxp.pojo.PageResult;
import com.lsxp.pojo.Student;
import com.lsxp.pojo.StudentQueryParam;

public interface StudentService {
    PageResult<Student> pageStudents(StudentQueryParam studentQueryParam);

    void saveStudent(Student student);

    Student getStudentById(Integer id);

    void updateStudent(Student student);

    void deleteStudents(Integer[] ids);

    void updateStudentViolation(Integer id, Integer score);
}
