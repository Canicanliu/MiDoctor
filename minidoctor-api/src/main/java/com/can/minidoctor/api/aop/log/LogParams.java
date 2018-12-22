package com.can.minidoctor.api.aop.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: ltm
 * @Descripion:
 * @Date: Created in 10:55 2018/12/8
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogParams {
    /**
     * log 说明
     *
     * @return
     */
    String value() default "";
}
