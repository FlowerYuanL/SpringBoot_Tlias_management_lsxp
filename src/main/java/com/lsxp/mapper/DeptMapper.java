package com.lsxp.mapper;

import com.lsxp.pojo.Dept;
import com.lsxp.pojo.Result;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /*
    * 查询全部部门
    * */
    @Select("select " +
            "dept.id,dept.name,dept.create_time,dept.update_time " +
            "from dept order by update_time desc ;")
    List<Dept> queryAll();


    /*
    * 根据ID删除部门
    * */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("INSERT into dept(name,update_time,create_time) values (#{name},#{updateTime},#{createTime})")
    void save(Dept dept);

    @Select("SELECT id,name,create_time,update_time from dept WHERE id = #{id}")
    Dept findByID(Integer id);

    @Update("UPDATE dept SET name = #{name} WHERE id = #{id}")
    void modifyById(Dept dept);
}
