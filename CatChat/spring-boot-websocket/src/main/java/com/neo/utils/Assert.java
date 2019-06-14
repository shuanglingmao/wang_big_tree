package com.neo.utils;


import com.google.common.base.Strings;

/**
 * Description: 断言
 *
 * @author shuangling.mao
 * @date 2019/6/13 18:25
 */
public class Assert {
    /**
     * 断言是否为真，如果为 {@code false} 抛出 {@code IllegalArgumentException} 异常<br>
     *
     * <pre class="code">
     * Assert.isTrue(i &gt; 0, "The value must be greater than zero");
     * </pre>
     *
     * @param expression 波尔值
     * @param errorMsgTemplate 错误抛出异常附带的消息模板，变量用{}代替
     * @param params 参数列表
     * @throws IllegalArgumentException if expression is {@code false}
     */
    public static void isTrue(boolean expression, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        if (false == expression) {
//            throw new IllegalArgumentException(StringUtils(errorMsgTemplate, params));
        }
    }

    public static <T> T notNull(T object, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        if (object == null) {
//            throw new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
        }
        return object;
    }
    /**
     * 断言对象是否不为{@code null} ，如果为{@code null} 抛出{@link IllegalArgumentException} 异常
     *
     * <pre class="code">
     * Assert.notNull(clazz);
     * </pre>
     *
     * @param <T> 被检查对象类型
     * @param object 被检查对象
     * @return 非空对象
     * @throws IllegalArgumentException if the object is {@code null}
     */
    public static <T> T notNull(T object) throws IllegalArgumentException {
        return notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    /**
     * 断言对象是否为{@code null} ，如果不为{@code null} 抛出{@link IllegalArgumentException} 异常
     *
     * <pre class="code">
     * Assert.isNull(value);
     * </pre>
     *
     * @param object 被检查对象
     * @throws IllegalArgumentException if the object is not {@code null}
     */
    public static void isNull(Object object) throws IllegalArgumentException {
        isNull(object, "[Assertion failed] - the object argument must be null");
    }


    /**
     * 断言对象是否为{@code null} ，如果不为{@code null} 抛出{@link IllegalArgumentException} 异常
     *
     * <pre class="code">
     * Assert.isNull(value, "The value must be null");
     * </pre>
     *
     * @param object 被检查的对象
     * @param errorMsgTemplate 消息模板，变量使用{}表示
     * @param params 参数列表
     * @throws IllegalArgumentException if the object is not {@code null}
     */
    public static void isNull(Object object, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        if (object != null) {
//            throw new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
        }
    }


    /**
     * 检查给定字符串是否为空白（null、空串或只包含空白符），为空抛出 {@link IllegalArgumentException}
     *
     * <pre class="code">
     * Assert.notBlank(name, "Name must not be blank");
     * </pre>
     *
     * @param text 被检查字符串
     * @param errorMsgTemplate 错误消息模板，变量使用{}表示
     * @param params 参数
     * @return 非空字符串
     * @throws IllegalArgumentException 被检查字符串为空白
     */
    public static String notBlank(String text, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        if (Strings.isNullOrEmpty(text)) {
//            throw new IllegalArgumentException(StringU.format(errorMsgTemplate, params));
        }
        return text;
    }



    /**
     * 检查给定字符串是否为空白（null、空串或只包含空白符），为空抛出 {@link IllegalArgumentException}
     *
     * <pre class="code">
     * Assert.notBlank(name, "Name must not be blank");
     * </pre>
     *
     * @param text 被检查字符串
     * @return 非空字符串
     * @throws IllegalArgumentException 被检查字符串为空白
     */
    public static String notBlank(String text) throws IllegalArgumentException {
        return notBlank(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
    }



}
