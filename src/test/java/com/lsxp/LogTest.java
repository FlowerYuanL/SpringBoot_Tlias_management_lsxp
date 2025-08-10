package com.lsxp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class LogTest {

    @Test
    public void testLog(){

//        System.out.println(LocalDateTime.now()+"开始计算。。。");
        log.debug("开始计算");
        int sum = 0;
        int[] nums = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        for(int num:nums){
            sum += num;
        }

//        System.out.println("sum="+sum);
        log.info("sum:{}",sum);
//        System.out.println(LocalDateTime.now()+"结束计算。。。");
        log.debug("结束计算");

//        log.trace("trace");
//        log.debug("debug");
//        log.info("info");
//        log.warn("warn");
//        log.error("error");

    }
}
