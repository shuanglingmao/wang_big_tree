package com.neo.config.rule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Description: 配置类测试
 *
 * @author shuangling.mao
 * @date 2019/6/17 16:35
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RuleConfigureTest {
    @Autowired
    private RuleConfigure ruleConfigure;
    @Test
    public void test() {
        System.out.println(ruleConfigure.getName());
    }
}