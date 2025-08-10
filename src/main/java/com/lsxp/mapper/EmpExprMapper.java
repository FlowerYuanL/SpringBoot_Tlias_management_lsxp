package com.lsxp.mapper;

import com.lsxp.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    void insertBatch(List<EmpExpr> emprList);
}
