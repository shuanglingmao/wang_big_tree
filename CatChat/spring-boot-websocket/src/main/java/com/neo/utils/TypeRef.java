package com.neo.utils;

import java.util.List;
import java.util.Map;

/**
 * @Description: 封装泛型
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/2
 * @Author 毛双领 <shuangling.mao>
 */
public class TypeRef<T> {
    public static void main(String[] args) {
        //适用于复杂类型的 参数类型 传参
        List<Map<String, Integer>> list1 = typeRef(new TypeRef<List<Map<String, Integer>>>());
    }

    private static <T> T typeRef(TypeRef<T> tTypeRef) {

        T result = null;

        return result;
    }
}
