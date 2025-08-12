package com.lsxp.service;

import com.lsxp.pojo.Clazz;
import com.lsxp.pojo.ClazzQueryParam;
import com.lsxp.pojo.PageResult;

public interface ClazzService {
    PageResult<Clazz> queryPage(ClazzQueryParam clazzQueryParam);
}
