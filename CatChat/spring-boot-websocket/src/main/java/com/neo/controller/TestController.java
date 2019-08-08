package com.neo.controller;

import com.neo.rpc.threadpool.CommonThreadPool;
import com.neo.rpc.threadpool.IAsynchronousHandler;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/6/28 0028
 * @Author 毛双领 <shuangling.mao>
 */
@RestController
public class TestController implements InitializingBean{

    @RequestMapping()
    public String testThreadLocal(String name) throws ExecutionException, InterruptedException {
        final Future<Object> execute = CommonThreadPool.execute(new IAsynchronousHandler<String>() {
            @Override
            public void executeAfter(Throwable t) {

            }

            @Override
            public void executeBefore(Thread t) {

            }
            @Override
            public String call() throws Exception {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i <= 100; i++) {
                    try { TimeUnit.MILLISECONDS.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
                    sb.append(name+i);
                }
                return sb.toString()+"执行完毕";
            }
        });
        return (String) execute.get();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestController初始化完毕··················");
    }
}
