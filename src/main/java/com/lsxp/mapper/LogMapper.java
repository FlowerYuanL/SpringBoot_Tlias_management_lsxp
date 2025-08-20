package com.lsxp.mapper;

import com.lsxp.pojo.LogQueryParam;
import com.lsxp.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {

    @Select("select o.*,e.name operateEmpName from operate_log o left join emp e on o.operate_emp_id = e.id order by id desc")
    List<OperateLog> pageQuery(LogQueryParam logQueryParam);
}
