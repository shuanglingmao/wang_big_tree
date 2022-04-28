package com.neo.strategy.factory;

import com.neo.strategy.VipStrategy;
import com.neo.utils.WebSocketUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Description: vip策略工厂
 *
 * @author shuangling.mao
 * @date 2019/6/10 17:55
 */
@Component
public class VipStrategyFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    //vip会员组
    private static Set<String> vipGroup = new HashSet<String>(){{
        add("毛双领");
        add("顶天立地智慧大将军");
        add("星际宇宙超级美少女");
        add("王屁呆");
        add("许嵩");
    }};

    /**
     * TODO 根据用户的会员级别获取不同的策略
     * @Description:
     * @author shuangling.mao
     * @date 2019/6/10 18:00
     * @param cardName
     * @return com.neo.strategy.VipStrategy
     */
    public VipStrategy getVipStrategy(String cardName) {
        if (vipGroup.contains(cardName)) {
            return (VipStrategy) applicationContext.getBean("fatherStrategy");
        }
        return (VipStrategy) applicationContext.getBean("loserStrategy");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        VipStrategyFactory.applicationContext = applicationContext;
    }
}
