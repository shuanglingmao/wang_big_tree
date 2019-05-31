package com.msl.util;

import com.msl.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * description: 克隆工具类
 *
 * @author yongjun.ji
 * @date 2018/6/26 18:29
 */
public class CloneUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CloneUtil.class);

    /**
     * 深克隆
     * @param originObj
     * @param <T>
     * @return
     */
    public static <T> T deepClone(T originObj) {
        if (originObj == null) {
            throw new RuntimeException("originObj can not be null!");
        }
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(originObj);
            bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(baos);
            close(oos);
            close(bais);
            close(ois);
        }
    }
    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("二狗");
        user.setCnnetGlobalConfigBean(new User.CnnetGlobalConfigBean());
        System.out.println(user.getCnnetGlobalConfigBean());
        System.out.println("-----------------");
        System.out.println(CloneUtil.deepClone(user).getCnnetGlobalConfigBean());
    }
}
