package com.lsxp.service;

import com.lsxp.pojo.Clazz;
import com.lsxp.pojo.ClazzQueryParam;
import com.lsxp.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> queryPage(ClazzQueryParam clazzQueryParam);

    void deleteClazz(Integer id);

    void saveClazz(Clazz clazz);

    Clazz getClazzById(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz> getAllClazz();
}
