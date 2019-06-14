package com.neo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 避免单位时间内重复提交注解    针对同一ip地址
 *
 * @author shuangling.mao
 * @date 2019/6/14 10:05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AvoidRepeatableCommit {
    /**
     * 指定时间内不可重复提交，单位秒
     * @return
     */
    long timeout() default 10;
    Class<?> retrunType() default String.class;
}
