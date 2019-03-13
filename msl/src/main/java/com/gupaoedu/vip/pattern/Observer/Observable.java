package com.gupaoedu.vip.pattern.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 */
public class Observable {

    /**关注呆姐的人 粉丝列表*/
    List<Observer> funs = new ArrayList<Observer>();

    /**
     * 有人关注呆姐   则粉丝列表将其添加进去
     * @param observer
     */
    public void addObserver(Observer observer) {
        funs.add(observer);
    }


    /**
     * 呆姐发微博了！
     */
    public void updateWeibo() {
        System.out.println("我是王屁呆，我发新动态了！");
        notifyFuns();
    }

    /**
     *  通知粉丝们 呆姐发微博了
     */
    public void notifyFuns() {
        for (Observer funs : funs) {
            funs.update(this);
        }
    }

}
