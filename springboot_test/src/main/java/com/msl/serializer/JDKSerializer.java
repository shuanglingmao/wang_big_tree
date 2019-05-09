package com.msl.serializer;

import java.io.*;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/5/9 10:15
 */
public class JDKSerializer implements Serializer<Object> {
    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if (!(object instanceof Serializable)) {
            throw new IllegalArgumentException(object.getClass().getCanonicalName()+"未实现序列化接口");
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(128);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new SerializationException("JDK序列化对象失败"+e);
        }
    }

    @Override
    public Object deserialize(byte[] source) throws SerializationException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(source);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        } catch (Exception e) {
            throw new SerializationException("JDK反序列化失败"+e);
        }
    }
}
