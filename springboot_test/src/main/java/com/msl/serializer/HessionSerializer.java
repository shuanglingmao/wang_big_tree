package com.msl.serializer;

/**
 * Description: hession序列化
 *
 * @author shuangling.mao
 * @date 2019/5/9 10:46
 */
public class HessionSerializer implements Serializer{
    @Override
    public byte[] serialize(Object o) throws SerializationException {
        return new byte[0];
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return null;
    }
}
