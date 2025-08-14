package com.lsxp.mapper;

import com.lsxp.pojo.Clazz;
import com.lsxp.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> findAll(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);

    void saveClazz(Clazz clazz);

    @Select("select * from clazz where id = #{id}")
    Clazz getClazzById(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz> getAllClazz();
}
