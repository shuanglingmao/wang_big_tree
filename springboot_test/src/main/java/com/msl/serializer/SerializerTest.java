package com.msl.serializer;

import com.msl.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Description: test
 *
 * @author shuangling.mao
 * @date 2019/5/9 10:26
 */
public class SerializerTest {
    private User user;
    @Before
    public void init() {
        user = new User();
        user.setAge(1);
        user.setName("二狗");
        user.setPhoneNum("17600207112");
    }
    @Test
    public void jdkSerialize() {
        Serializer jdkSerializer = new JDKSerializer();
        //序列化
        byte[] bytes = jdkSerializer.serialize(user);
        System.out.println(Arrays.toString(bytes));

        //反序列化
        Object deserialize = jdkSerializer.deserialize(bytes);
        System.out.println(deserialize);

    }
}
