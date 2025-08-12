package com.lsxp.mapper;

import com.lsxp.pojo.Emp;
import com.lsxp.pojo.EmpQueryParam;
import com.lsxp.pojo.GenderOption;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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

    void deleteBatch(List<Integer> ids);

    Emp findById(Integer id);

    void update(Emp emp);

    /*
    * 统计员工职位信息，人数
    * */
    @MapKey("position")
    List<Map<String,Object>> countJob();

    /*
    * 统计员工性别信息，人数
    * */
    List<GenderOption> countGender();
}
