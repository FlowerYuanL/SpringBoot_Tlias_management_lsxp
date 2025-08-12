package com.lsxp.mapper;

import com.lsxp.pojo.Clazz;
import com.lsxp.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> findAll(ClazzQueryParam clazzQueryParam);
}
