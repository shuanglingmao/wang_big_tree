package com.msl.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author shuangling.mao
 * @date 2019/3/11 11:11
 */
public class MapUtils {
    /**
     * 把list转换为HashMap
     * @param list
     * @param key
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> Map<K,V> transListToMap(List<V> list, Key<K,V> key) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K,V> map = new HashMap<>(list.size());
        for (V v : list) {
            map.put(key.getKey(v), v);
        }
        return map;
    }

    public interface Key<K,V> {
        K getKey(V v);
    }
}
