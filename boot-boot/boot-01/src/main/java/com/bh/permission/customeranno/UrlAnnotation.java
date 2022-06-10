package com.bh.permission.customeranno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 拦截器解析这个注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlAnnotation {
    /**
     * 操作类型(type):添加,删除,修改,插入
     * 
     */
    String type();
}