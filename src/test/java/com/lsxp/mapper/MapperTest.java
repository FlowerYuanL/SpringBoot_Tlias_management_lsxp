package com.lsxp.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsxp.pojo.GenderOption;
import com.lsxp.utils.MyBatisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
class MapperTest {

    private EmpMapper empMapper;
    private SqlSession sqlSession;

    @BeforeEach
    public void setUp(){
        sqlSession = MyBatisUtil.getSqlSession();
        empMapper = sqlSession.getMapper(EmpMapper.class);
    }

    @Test
    void countJob() {
        List<Map<String,Object>> list = empMapper.countJob();
        System.out.println(list);
    }

    @Test
    void countGender(){
        List<GenderOption> genderOptions = empMapper.countGender();
        List<GenderOption> newList = genderOptions.stream().filter(genderOption -> !genderOption.getName().equals("未定义性别")).collect(Collectors.toList());
        System.out.println(newList);
    }



    @AfterEach
    public void tearDown(){
        sqlSession.close();
    }
}