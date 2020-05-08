package com.msl.guava.collection;

import com.google.common.collect.MapDifference;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 一个Map的工具类
 * @author msl on 2020/5/8.
 */
public final class Maps {

    public static <K,V> Map<K,V> filterKeys(Map<K,V> unFilteredMap, Predicate<K> keyPredicate) {
        Map<K, V> filteredMap = unFilteredMap.entrySet()
                .stream()
                .filter(entry -> keyPredicate.test(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return filteredMap;
    }


    public static <K,V> Map<K,V> filterValues(Map<K,V> unFilteredMap, Predicate<V> valuePredicate) {
        Map<K, V> filteredMap = unFilteredMap.entrySet()
                .stream()
                .filter(entry -> valuePredicate.test(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return filteredMap;
    }

    public static <K,V> Map<K,V> filterEntries(Map<K,V> unFilteredMap, Predicate<Map.Entry<K,V>> entryPredicate) {
        Map<K, V> filteredMap = unFilteredMap.entrySet()
                .stream()
                .filter(entry -> entryPredicate.test(entry))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return filteredMap;
    }

    public static <K,V,E> Map<K,V> collectionToMap(Collection<E> collection, Function<E,K> keyFunction, Function<E,V> valueFunction) {
      if (collection == null || collection.isEmpty()) {
          return Collections.EMPTY_MAP;
      }
      return collection.stream().collect(Collectors.toMap(keyFunction,valueFunction));
    };

    private <K,V> Predicate<Map.Entry<K,V>> get1112(Predicate<K> keyPredicate) {
        return entry -> keyPredicate.test(entry.getKey());
    }

    private <K,V> Predicate<Map.Entry<K,V>> get111(Predicate<V> valuePredicate) {
        return entry -> valuePredicate.test(entry.getValue());
    }



    private static enum EntryPredicate implements Predicate<Map.Entry<?,?>> {
        KEY_PREDICATE {
            @Override
            public boolean test(Map.Entry<?, ?> entry) {
                return false;
            }
        },
        VALUE_PREDICATE {
            @Override
            public boolean test(Map.Entry<?, ?> entry) {
                return false;
            }
        },
        ENTRY_PREDICATE {
            @Override
            public boolean test(Map.Entry<?, ?> entry) {
                return false;
            }
        };


        public static void main(String[] args) {
            HashMap<String, Integer> map = new HashMap<>();
            map.put("张三", 1);
            Map<String, Integer> filteredMap = Maps.filterKeys(map, key -> key.equals("张三"));
            System.out.println(filteredMap);

            Map<String, Integer> map1 = com.google.common.collect.Maps.filterKeys(map, key -> !key.equals("张三"));
            System.out.println(map1);
            HashMap<Object, Object> map2 = new HashMap<>();
            HashMap<Object, Object> map3 = new HashMap<>();
            map2.put(1, 1);
            map2.put(2, 2);
            map2.put(3, 3);
            map2.put(5,5);
            map3.put(1, 1);
            map3.put(2, 2);
            map3.put(3, 3);
            map3.put(4, 4);
            MapDifference<Object, Object> difference = com.google.common.collect.Maps.difference(map2, map3);
            System.out.println(difference);

        }

    }
}
