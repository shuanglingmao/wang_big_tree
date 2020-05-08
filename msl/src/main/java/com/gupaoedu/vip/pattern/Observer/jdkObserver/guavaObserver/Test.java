package com.gupaoedu.vip.pattern.Observer.jdkObserver.guavaObserver;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;


/**
 * @author msl on 2020/3/28.
 */
public class Test {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus("王屁呆");


        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new EventDemo("aaa"));
        eventBus.post(new EventDemo("bbb"));
        eventBus.post(new EventDemo("ccc"));

        System.out.println(listener.getLastMessage());

    }
}


class EventDemo {
    private String message;

    public EventDemo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

class EventListener{
    public String lastMessage = "";

    @Subscribe
    public void listen(EventDemo event) {
        lastMessage = event.getMessage();
        System.out.println("message:"+ lastMessage);
    }

    public String getLastMessage() {
        return lastMessage;
    }
}

