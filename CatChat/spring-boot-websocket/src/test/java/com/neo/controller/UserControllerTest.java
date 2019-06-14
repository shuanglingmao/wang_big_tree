package com.neo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.concurrent.TimeUnit;

/**
 * Description: 测试
 *
 * @author shuangling.mao
 * @date 2019/6/14 16:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerTest {
    private MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void login() throws Exception {
        new Thread(()->{
            String responseString = null;
            try {
                responseString = mockMvc.perform(MockMvcRequestBuilders.post("/user/test?num=1"))
                        .andReturn().getResponse().getContentAsString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("result : "+responseString);
        },"thread111").start();

        new Thread(()->{
            String responseString = null;
            try {
                responseString = mockMvc.perform(MockMvcRequestBuilders.post("/user/test?num=1"))
                        .andReturn().getResponse().getContentAsString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("result : "+responseString);
        },"thread222").start();

        TimeUnit.MINUTES.sleep(1);

        //TODO  特别提示 Mock测试并非 真正意义上的 http请求

    }

    @Test
    public void register() throws Exception {
    }

}