package com.neo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 避免同一时间同一用户多次提交
 *
 * @author shuangling.mao
 * @date 2019/6/14 10:05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AvoidMultipleCommit {
}
