package com.msl.serializer;


/**
 * Description: 序列化器
 *
 * @author shuangling.mao
 * @date 2019/5/9 10:09
 */
public interface Serializer<T> {

    /**
     * 序列化
     * @param t
     * @return
     * @throws SerializationException
     */
    byte[] serialize(T t) throws SerializationException;

    /**
     * 反序列化
     * @param bytes
     * @return
     * @throws SerializationException
     */
    T deserialize(byte[] bytes) throws SerializationException;
}
