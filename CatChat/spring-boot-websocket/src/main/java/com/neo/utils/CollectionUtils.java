package com.neo.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/3 0003
 * @Author 毛双领 <shuangling.mao>
 */
public class CollectionUtils {

    public <T,R> Collection<R> transform(Collection<T> collection,Function<T, R> function) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }

        Object[] objects = new Object[]{};
        int[] a = new int[]{};
        for (T t : collection) {
            R apply = function.apply(t);
        }

        if (collection instanceof ArrayList) {
            return null;
        }
        return null;
    }


    public <T,R> List<R> transform(List<T> collection, Function<T, R> function) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        return null;
    }
}
