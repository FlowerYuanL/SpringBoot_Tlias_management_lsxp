package com.lsxp.mapper;


import com.lsxp.pojo.EmpLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpLogMapper {

    @Insert("INSERT INTO emp_log(date,msg,result) values (#{date},#{msg},#{result})")
    void insert(EmpLog empLog) ;
}
