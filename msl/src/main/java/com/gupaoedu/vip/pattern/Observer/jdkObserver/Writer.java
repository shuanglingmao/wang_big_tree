package com.gupaoedu.vip.pattern.Observer.jdkObserver;

import java.util.Observable;

public class Writer extends Observable {
    private String name;
    private String lastBookName;

    public Writer(String name) {
        this.name = name;
        WriterManger.getInstance().addWriter(this);
    }

    /**
     * 作者发布新小说
     * @return
     */
    public void publish(String bookName) {
        System.out.println(name+"发布了新书"+"《"+bookName+"》");
        this.lastBookName = bookName;
        setChanged();
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastBookName() {
        return lastBookName;
    }

    public void setLastBookName(String lastBookName) {
        this.lastBookName = lastBookName;
    }
}
