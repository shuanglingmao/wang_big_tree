package com.neo.strategy.impl;

import com.neo.strategy.VipStrategy;
import org.springframework.stereotype.Component;

/**
 * Description: 屌丝会员
 *
 * @author shuangling.mao
 * @date 2019/6/10 17:52
 */
@Component
public class LoserStrategy implements VipStrategy {
    /**
     * 发消息
     *
     * @param name
     * @return
     */
    @Override
    public String getSentMsgWrap(String name) {
        return "穷逼屌丝用户：["+ name + "]:";
    }

    /**
     * 进入聊天室
     *
     * @param name
     * @return
     */
    @Override
    public String getInRoomWrap(String name) {
        return "欢迎穷逼屌丝用户：[" + name + "] 溜到聊天室,请文明聊天！";
    }

    /**
     * 离开聊天室
     *
     * @param name
     * @return
     */
    @Override
    public String getOutRoomWrap(String name) {
        return "穷逼屌丝会员[" + name + "] 已经滚出聊天室了！";
    }
}
