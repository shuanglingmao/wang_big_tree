package com.neo;

import com.neo.config.websocket.MyEndpointConfigure;
import com.neo.service.RedisService;
import com.neo.strategy.factory.VipStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import static com.neo.utils.WebSocketUtils.ONLINE_USER_SESSIONS;
import static com.neo.utils.WebSocketUtils.sendMessageAll;

@RestController
@ServerEndpoint(value = "/chat-room/{username}",configurator = MyEndpointConfigure.class)
public class ChatRoomServerEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(ChatRoomServerEndpoint.class);
    @Autowired
    private VipStrategyFactory vipStrategyFactory;
    @Autowired
    private RedisService redisService;

    /**
     * 有用户连接
     * @param username
     * @param session
     */
    @OnOpen
    public void openSession(@PathParam("username") String username, Session session) {
        ONLINE_USER_SESSIONS.put(username, session);
        String message = vipStrategyFactory.getVipStrategy(username).getInRoomWrap(username);
        logger.info("用户登录："+message);
        sendMessageAll(message);
    }

    /**
     * 用户发消息
     * @param username
     * @param message
     */
    @OnMessage
    public void onMessage(@PathParam("username") String username, String message) {
        sendMessageAll(vipStrategyFactory.getVipStrategy(username).getSentMsgWrap(username) + message);
        logger.info("用户:["+username+"] 发送消息："+message);
    }


    /**
     * 用户断开连接
     * @param username
     * @param session
     */
    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        //当前的Session 移除
        ONLINE_USER_SESSIONS.remove(username);
        //并且通知其他人当前用户已经离开聊天室了
        sendMessageAll(vipStrategyFactory.getVipStrategy(username).getOutRoomWrap(username));
        try {
            session.close();
        } catch (IOException e) {
            logger.error("onClose error",e);
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            logger.error("onError excepiton",e);
        }
        logger.info("Throwable msg "+throwable.getMessage());
    }


}