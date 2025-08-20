package com.lsxp.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsxp.mapper.OperateLogMapper;
import com.lsxp.pojo.OperateLog;
import com.lsxp.pojo.UserContextHoler;
import com.lsxp.pojo.UserDTO;
import com.lsxp.utils.HttpUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

    //注入Mapper接口
    @Autowired
    private OperateLogMapper operateLogMapper;

    //注入HttpUtils，获取用户id
    @Autowired
    private HttpUtils  httpUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut("@annotation(com.lsxp.annotation.LogAnnotation)")
    private void pointCutByAnnotation() {}

    @Pointcut("execution(* com.lsxp.controller.*.*(..))")
    private void pointCutByExecution(){}


    /*
    * 记录日志信息
    * */
    @Around("pointCutByAnnotation()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录当前时间
        Long begin = System.currentTimeMillis();
        //获取用户id通过Local
        UserDTO user = UserContextHoler.getUser();
        Integer id = user.getId();
        //记录执行方法的全类名
        String clazzName = joinPoint.getTarget().getClass().getName();
        //记录执行方法的方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        //记录方法运行时的参数
        Object[] args = joinPoint.getArgs();
        String argsJson = objectMapper.writeValueAsString(args);
        //运行方法,记录方法运行的返回值
        Object obj = joinPoint.proceed();
        String objJson = objectMapper.writeValueAsString(obj);
        //记录当前时间
        Long end = System.currentTimeMillis();
        //计算方法的执行时长
        Long time = end - begin;
        //输出日志
        log.info("[LogAspect]:执行者序号:{},方法=>{}.{}(执行时的参数:{}),执行耗时:{}ms,返回值:{}",id,clazzName,methodName, Arrays.toString(args),time,objJson);
        //将结果封装进OperateLog类中
        OperateLog operateLog = OperateLog.builder()
                .operateEmpId(id)
                .operateTime(LocalDateTime.now())
                .className(clazzName)
                .methodName(methodName)
                .methodParams(argsJson)
                .returnValue(objJson)
                .costTime(time)
                .build();
        //调用Mapper接口，将结果保存进数据库
        operateLogMapper.insert(operateLog);
        //返回结果
        return obj;
    }
}
