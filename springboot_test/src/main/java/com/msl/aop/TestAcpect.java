package com.msl.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/3/15 10:20
 */
@Aspect
@Component
@Order(1)
public class TestAcpect {
    /**
     * 定义切入点，切入点为com.msl.controller下的所有函数
     */
    @Pointcut("execution(public * com.msl.controller..*.*(..))")
    public void controller(){}

    /**
     * 前置通知：在连接点之前执行的通知
     * @param joinPoint
     * @throws Throwable
     */

    @Before("controller()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        System.out.println("前置通知~~~~~~~~~~~~~~~");
        System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("参数 : " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("------------------------------------------------------");
    }

    /**
     * 环绕通知,环绕增强，相当于MethodInterceptor
     */
    @Around("controller()")
    public Object arround(ProceedingJoinPoint pjp) {
        System.out.println("表达式环绕通知~~~~~~~~~~~~~~~");
        try {
            Object o =  pjp.proceed();
            System.out.println("方法环绕proceed，结果是 :" + o);
            System.out.println("------------------------------------------------------");
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 加注解的切入点最好写   切入带Msl注解的所有方法
     * @param jp
     */
    @Around(value = "@annotation(com.msl.annotation.Msl)")
    public Object annotation(ProceedingJoinPoint jp) throws Throwable {
        final Object[] args = jp.getArgs();
        System.out.println("注解环绕~~~~~~~~~~~~~~~");
        System.out.println("带Msl注解的参数是"+Arrays.toString(args));
        final Object proceed = jp.proceed();
        System.out.println("带Msl注解返回结果是"+JSONObject.toJSONString(proceed));
        System.out.println("注解切点");
        System.out.println("------------------------------------------------------");
        return proceed;
    }
}
