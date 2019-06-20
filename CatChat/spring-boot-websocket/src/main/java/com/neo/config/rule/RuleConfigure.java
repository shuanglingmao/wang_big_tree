package com.neo.config.rule;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Description: 规则配置
 *
 * @author shuangling.mao
 * @date 2019/6/17 16:15
 */
@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "rule")
@PropertySource(value = "classpath:/rule.properties")
public class RuleConfigure {
    @Value("${rule.alert_srms_order_pc_save}")
    private String name;
}
