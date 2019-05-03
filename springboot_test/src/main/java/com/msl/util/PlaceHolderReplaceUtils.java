package com.msl.util;

import com.google.common.base.Strings;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Desc: 占位符替换, 占位符表示为：{@code {placeholder}}；
 * <p>
 * 示例：替换如下{xxx}占位符中的内容
 * <pre>"名字:{name},年龄:{age},学校:{school}"</pre>
 *
 * @author huiming.guo
 */
public class PlaceHolderReplaceUtils {

    private static Pattern pattern = Pattern.compile("\\{(.*?)\\}");

    /**
     * 替换字符串占位符, 字符串中使用{key}表示占位符
     *
     * @param sourceString 需要匹配的字符串，示例："名字:{name},年龄:{age},学校:{school}";
     * @param param        参数集,Map类型
     * @return
     */
    public static String replaceWithMap(String sourceString, Map<String, String> param) {
        if (Strings.isNullOrEmpty(sourceString) || CollectionUtils.isEmpty(param)) {
            return sourceString;
        }

        String targetString = sourceString;
        Matcher matcher = pattern.matcher(sourceString);
        while (matcher.find()) {
            try {
                String key = matcher.group();
                String keyclone = key.substring(1, key.length() - 1).trim();
                String value = param.get(keyclone);
                if (value != null) {
                    targetString = targetString.replace(key, value);
                }
            } catch (Exception e) {
                throw new RuntimeException("String formatter failed", e);
            }
        }
        return targetString;
    }

    /**
     * 替换字符串占位符, 字符串中使用{key}表示占位符
     * <p>
     * 利用反射 自动获取对象属性值 (必须有get方法)
     *
     * @param sourceString 需要匹配的字符串
     * @param param        参数集
     * @return
     */
    public static String replaceWithObject(String sourceString, Object param) {
        if (Strings.isNullOrEmpty(sourceString) || null == param) {
            return sourceString;
        }

        String targetString = sourceString;

        PropertyDescriptor pd;
        Method getMethod;

        // 匹配{}中间的内容 包括括号
        Matcher matcher = Pattern.compile("\\{(.*?)\\}").matcher(sourceString);
        while (matcher.find()) {
            String key = matcher.group();
            String holderName = key.substring(1, key.length() - 1).trim();
            try {
                pd = new PropertyDescriptor(holderName, param.getClass());
                getMethod = pd.getReadMethod(); // 获得get方法
                Object value = getMethod.invoke(param);
                if (value != null) {
                    targetString = targetString.replace(key, value.toString());
                }
            } catch (Exception e) {
                throw new RuntimeException("String formatter failed", e);
            }
        }
        return targetString;
    }

    /**
     * 查找String中的占位符keys；<br/>
     * 示例： "名字:{name},年龄:{age},学校:{school}"， 则返回：Set[name,age,school]
     * <p>
     * pattern示例：
     * <pre> {@code
     *  // 尖括号：<placeHolder> 表示为占位符
     *  Pattern pattern = Pattern.compile("\\<(.*?)\\>");
     *
     *  // 大括号：{placeHolder} 表示为占位符， 上面的示例中就使用{}作为占位符
     *  Pattern pattern = Pattern.compile("\\{(.*?)\\}");
     * }
     * </pre>
     *
     * @param sourceString
     * @param pattern
     * @return
     */
    public static Set<String> findPlaceHolderKeys(String sourceString, Pattern pattern) {
        Set<String> placeHolderSet = new HashSet<String>();

        if (Strings.isNullOrEmpty(sourceString) || null == pattern) {
            return placeHolderSet;
        }

        String targetString = sourceString;
        Matcher matcher = pattern.matcher(sourceString);
        while (matcher.find()) {
            String key = matcher.group();  //示例: {name}
            String placeHolder = key.substring(1, key.length() - 1).trim();  //示例： name
            placeHolderSet.add(placeHolder);
        }

        return placeHolderSet;
    }
}