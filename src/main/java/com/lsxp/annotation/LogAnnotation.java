package com.lsxp.annotation;


import java.lang.annotation.*;


/*
* 声明注解
* */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    /*
    * 可选属性，用于描述方法
    * */
    String value() default "";
}
