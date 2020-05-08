package com.gupaoedu.vip.pattern.Observer.text;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 * @author msl on 2020/4/10.
 */
public class Observable {
    /**
     * 粉丝列表
     */
    private List<Observer> fensiList = new ArrayList<>();

    public void add(Observer observer) {
        this.fensiList.add(observer);
    }

    public void notifyFuns() {

        fensiList.stream().forEach(fensi -> {
            try {
                fensi.execute();
            } catch (Exception e) {
                System.out.println("通知报错");
            }
        });
    }


}
