package com.neo.strategy;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Description: 会员策略接口
 *
 * @author shuangling.mao
 * @date 2019/6/10 17:38
 */
public interface VipStrategy {
    /**
     * 发消息
     * @param name
     * @return
     */
    String getSentMsgWrap(String name);
    /**
     * 进入聊天室
     * @param name
     * @return
     */
    String getInRoomWrap(String name);
    /**
     * 离开聊天室
     * @param name
     * @return
     */
    String getOutRoomWrap(String name);
}
