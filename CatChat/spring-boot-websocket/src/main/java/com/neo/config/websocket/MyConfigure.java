package com.neo.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 解决WebSocket不能Autowired  注入的问题
 *
 * @author shuangling.mao
 * @date 2019/6/10 18:22
 */
@Configuration
public class MyConfigure {
    @Bean
    public MyEndpointConfigure newConfigure() {
        return new MyEndpointConfigure();
    }
}
