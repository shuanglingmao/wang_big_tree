package com.neo.test;

import com.alibaba.fastjson.JSONObject;
import com.neo.utils.TypeRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/7/2 0002
 * @Author 毛双领 <shuangling.mao>
 */
public class DoNothing {
    public static void main(String[] args) {

        List<Map<String, Integer>> list1 = typeRef(new TypeRef<List<Map<String, Integer>>>());
    }

    private static <T> T typeRef(TypeRef<T> tTypeRef) {

        T result = null;

        return result;
    }

}

