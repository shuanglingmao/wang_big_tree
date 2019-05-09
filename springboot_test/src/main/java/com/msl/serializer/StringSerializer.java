package com.msl.serializer;

import java.nio.charset.Charset;

/**
 * Description: string序列化反序列化
 *
 * @author shuangling.mao
 * @date 2019/5/9 10:35
 */
public class StringSerializer implements Serializer{

    private Charset charset;




    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return new byte[0];
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return null;
    }
}
