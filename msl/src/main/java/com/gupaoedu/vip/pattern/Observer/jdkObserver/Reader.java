package com.gupaoedu.vip.pattern.Observer.jdkObserver;


import java.util.Observable;
import java.util.Observer;

/**
 * 读者类 实现观察者接口
 */
public class Reader implements Observer {
    private String name;

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Writer) {
            Writer writer = (Writer) o;
            System.out.println(name+"知道"+writer.getName()+"发布了新书"+"《"+writer.getLastBookName()+"》");
        }
    }

    /**
     * 读者关注作者
     * 将读者添加进作者的关注这列表
     */
    public void subscribe(String writerName) {
        WriterManger.getInstance().getWriterByName(writerName).addObserver(this);
    }

    /**
     * 当然读者也可以取消关一位作者  意味着将读者从作者的关注这列表移除
     * @param writerName
     */
    public void unSubscribe(String writerName) {
        WriterManger.getInstance().getWriterByName(writerName).deleteObserver(this);
    }



    public Reader(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
