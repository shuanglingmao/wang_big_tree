package util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * $Description:
 *
 * @author shuangling.mao
 * @date 2019-05-02 22:01
 */

public class FilterUtils {

    public static <T> void filter(Collection<T> collection,Filter<T> filter) {
        filter(collection.iterator(),filter);
    }


    public static <K,V> void filter(Map<K,V> map, Filter<K> filter) {
        filter(map.keySet().iterator(),filter);
    }

    private static <T>void filter(Iterator<T> iterator,Filter<T> filter) {
        while (iterator.hasNext()) {
            if (filter.execute(iterator.next())) {
                iterator.remove();
            }
        }
    }


    public interface Filter<T>{
        boolean execute(T t);
    }
}
