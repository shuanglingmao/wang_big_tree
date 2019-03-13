package com.gupaoedu.vip.pattern.Observer.jdkObserver;

import java.util.HashMap;
import java.util.Map;

/**
 * 平台  管理一份作者列表
 */
public class WriterManger {
    private Map<String, Writer> map = new HashMap<String, Writer>();

    /**
     * 添加作者
     * @param writer
     */
    public WriterManger addWriter(Writer writer) {
        map.put(writer.getName(),writer);
        return this;
    }

    /**
     * 根据作者名字获得作者
     * @param name
     * @return
     */
    public Writer getWriterByName(String name) {
        return map.get(name);
    }

    /**
     * 单例
     */
    private WriterManger() {}

    private static class WriterMangerInstance{
        private static WriterManger writerManger = new WriterManger();
    }

    public static WriterManger getInstance() {
        return WriterMangerInstance.writerManger;
    }

}
