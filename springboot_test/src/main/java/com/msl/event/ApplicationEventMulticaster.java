package com.msl.event;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 *
 * @Description:  默认情况下 所有监听器都是在一个线程中执行的
 * 可以通过此形式将监听器放到额外的线程去执行。
 * 1.   <!-- 任务执行器 -->
    <task:executor id="payment-status-update-thread" pool-size="10"/>
    <bean id="applicationEventMulticaster" class="org.springframework.context.event.SimpleApplicationEventMulticaster">
    <property name="taskExecutor" ref="payment-status-update-thread"/>
    </bean>
   2.通过配置类实现
 * @author shuangling.mao
 * @date 2019/5/10 10:34
 * @param
 * @return
 */
@Component("applicationEventMulticaster")
public class ApplicationEventMulticaster extends SimpleApplicationEventMulticaster {
    public ApplicationEventMulticaster() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("事件通知线程-pool-%d").build();
        setTaskExecutor(new ThreadPoolExecutor(3, 5,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10000), namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy()));
    }
}