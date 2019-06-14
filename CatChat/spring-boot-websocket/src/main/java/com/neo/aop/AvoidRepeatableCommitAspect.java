package com.neo.aop;

import com.neo.annotation.AvoidRepeatableCommit;
import com.neo.service.RedisService;
import com.neo.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Description: 防止重复提交aop切面
 *
 * @author shuangling.mao
 * @date 2019/6/14 10:12
 */
@Aspect
@Component
@Slf4j
public class AvoidRepeatableCommitAspect {
    @Autowired
    private RedisService redisService;

    @Around("@annotation(com.neo.annotation.AvoidRepeatableCommit)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = IPUtils.getIP(request);
        //获取注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //获取请求参数
        Map query = request.getParameterMap();
        //目标类、方法
        String className = method.getDeclaringClass().getName();
        String name = method.getName();
        String ipKey = String.format("%s#%s#%s#%s", ip, className, name, query);
        int hashCode = Math.abs(ipKey.hashCode());
        String key = String.format("%s_%d", ip, hashCode);
        log.info("ipKey={},hashCode={},key={}", ipKey, hashCode, key);
        AvoidRepeatableCommit avoidRepeatableCommit = method.getAnnotation(AvoidRepeatableCommit.class);
        long timeout = avoidRepeatableCommit.timeout();
        if (timeout < 0) {
            //默认30 秒
            timeout = 30;
        }
        String value = redisService.get(key);
        if (Strings.isNotBlank(value)) {
            final Class<?> aClass = avoidRepeatableCommit.retrunType();
            //TODO 根据返回值类型做处理
            return "亲! 你点的太快了";
        }
        redisService.set(key, "-", timeout, TimeUnit.SECONDS);
        //执行方法*/
        return point.proceed();
    }



}
