package com.neo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.neo.utils.WebSocketUtils.ONLINE_USER_SESSIONS;
import static com.neo.utils.WebSocketUtils.sendMessageAll;

@RestController
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(ChatRoomServerEndpoint.class);
    private static Set<String> vipName = new HashSet<String>(){{
        add("毛双领");
        add("顶天立地智慧大将军");
    }};
    @OnOpen
    public void openSession(@PathParam("username") String username, Session session) {
        ONLINE_USER_SESSIONS.put(username, session);
        String message = "欢迎穷逼屌丝用户[" + username + "] 溜到聊天室,请文明聊天！";
        if (vipName.contains(username)) {
            message = "欢迎亲爹VIP用户[" + username + "] 大驾光临聊天室,请文明聊天！";
        }
        logger.info("用户登录："+message);
        sendMessageAll(message);
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username, String message) {
        if (vipName.contains(username)) {
            username = "亲爹VIP会员："+username;
        } else {
            username = "穷逼屌丝会员："+username;
        }
        sendMessageAll("[" + username + "] : " + message);
        logger.info("发送消息："+message);
    }

    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        //当前的Session 移除
        ONLINE_USER_SESSIONS.remove(username);
        //并且通知其他人当前用户已经离开聊天室了
        if (vipName.contains(username)) {
            sendMessageAll("亲爹VIP会员[" + username + "] 已经不屑一顾的离开聊天室了！");
        } else {
            sendMessageAll("穷逼屌丝会员[" + username + "] 已经滚出聊天室了！");
        }
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