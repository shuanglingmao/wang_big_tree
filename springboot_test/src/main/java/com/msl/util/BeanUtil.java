//package com.msl.util;
//import bean.ModifyPara;
//import org.apache.commons.beanutils.BeanUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * Description: 对象工具类
// *
// * @author shuangling.mao
// * @date 2018/6/19 18:46
// */
//public class BeanUtil {
//    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);
//
//    public static <T> boolean isChanged(ModifyPara<T> modifyPara, String fieldName) {
//        return isChanged(modifyPara.getOriginVO(), modifyPara.getModifyVO(), fieldName);
//    }
//
//    /**
//     * 对象某一个字段的值是否改变了
//     *
//     * @param origin
//     * @param now
//     * @param fieldName
//     * @return
//     */
//    public static <T> boolean isChanged(T origin, T now, String fieldName) {
//        Object oldVal = null;
//        Object newVal = null;
//        try {
//            if (origin != null) {
//                oldVal = BeanUtils.getProperty(origin, fieldName);
//            }
//            if (now != null) {
//                newVal = BeanUtils.getProperty(now, fieldName);
//            }
//        } catch (Exception e) {
//            logger.error("获取字段" + fieldName + "的值异常", e);
//        }
//        if (oldVal == null) {
//            return newVal != null;
//        } else {
//            return !oldVal.equals(newVal);
//        }
//    }
//
//    /**
//     * 两个对象是否相等
//     *
//     * @param t
//     * @param t2
//     * @param <T>
//     * @return
//     */
//    public static <T> boolean isEquals(T t, T t2) {
//        if (t == null) {
//            return t2 == null;
//        } else {
//            return t.equals(t2);
//        }
//    }
//
//    /**
//     * 两个对象是否不相等
//     *
//     * @param t
//     * @param t2
//     * @param <T>
//     * @return
//     */
//    public static <T> boolean isNotEquals(T t, T t2) {
//        return !isEquals(t, t2);
//    }
//}
//
