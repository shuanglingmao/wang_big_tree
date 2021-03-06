package com.neo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * Description: 防止同一用户多次提交
 *
 * @author shuangling.mao
 * @date 2019/6/14 10:12
 */
@Aspect
@Component
@Slf4j
public class AvoidMultipleCommitAspect {

    @Around("@annotation(com.neo.annotation.AvoidMultipleCommit)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("你已经别我代理了");
        return point.proceed();
    }



}
