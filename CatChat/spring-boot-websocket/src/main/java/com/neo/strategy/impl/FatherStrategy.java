package com.neo.strategy.impl;

import com.neo.service.RedisService;
import com.neo.strategy.VipStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 亲爹VIP会员待遇
 *
 * @author shuangling.mao
 * @date 2019/6/10 17:46
 */
@Component
public class FatherStrategy implements VipStrategy{

    @Autowired
    private RedisService redisService;

    /**
     * 发消息
     *
     * @param name
     * @return
     */
    @Override
    public String getSentMsgWrap(String name) {

        return "亲爹VIP会员<span style='color:red'>["+name+"]</span>:";
    }

    /**
     * 进入聊天室
     *
     * @param name
     * @return
     */
    @Override
    public String getInRoomWrap(String name) {
        return "欢迎亲爹VIP用户<span style='color:red'>[" + name + "]</span> 大驾光临聊天室,请文明聊天！";
    }

    /**
     * 离开聊天室
     *
     * @param name
     * @return
     */
    @Override
    public String getOutRoomWrap(String name) {
        return "亲爹VIP会员<span style='color:red'>[" + name + "]</span> 已经不屑一顾的离开聊天室了！";
    }
}
