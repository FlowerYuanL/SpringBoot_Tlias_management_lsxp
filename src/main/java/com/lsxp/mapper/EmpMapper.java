package com.lsxp.mapper;

import com.lsxp.pojo.Emp;
import com.lsxp.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {
//=================================原始实现方式=====================================
//    @Select("select e.*,d.name deptName " +
//            "from emp e left join dept d on e.dept_id = d.id " +
//            "ORDER BY e.update_time DESC " +
//            "limit #{start},#{pageSize};")
//    List<Emp> findAll(Integer start,Integer pageSize);
//
//    @Select("SELECT COUNT(*) FROM emp")
//    Long count();

//===================================PageHelper===================================

//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id ORDER BY e.update_time DESC ")
//    List<Emp> findAll();

    List<Emp> findAll(EmpQueryParam empQueryParam);

    void insert(Emp emp);

    void delete(Integer[] ids);
}
