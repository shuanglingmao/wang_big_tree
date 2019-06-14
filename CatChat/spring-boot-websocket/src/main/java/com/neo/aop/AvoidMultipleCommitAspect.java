package com.neo.aop;

import com.neo.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
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
//@Order()
public class AvoidMultipleCommitAspect {
    @Autowired
    private RedisService redisService;





}
