package com.myself;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.junit.Test;

import java.util.Map;

public class GuavaAPi {
    /**
     * 判断字符串是否为空
     */
    @Test
    public void TestString() {
        //Strings.isNullOrEmpty(input) demo
        String input = "";
        System.out.println(Strings.isNullOrEmpty(input));
    }

    /**
     * 获得两个字符串相同的前缀或者后缀
     */
    @Test
    public void TestString1() {
        String a = "wang dai dai shi yi ge 小仙女";
        String b = "wang dai dai bu 小仙女";
        String commonPrefix = Strings.commonPrefix(a, b);
        System.out.println("公共前缀：" + commonPrefix);
        String commonSuffix = Strings.commonSuffix(a, b);
        System.out.println("公共后缀：" + commonSuffix);
    }

    /**
     * Strings的padStart和padEnd方法来补全字符串
     */
    @Test
    public void TestString2() {
        //最小长度
        int minLenth = 5;
        String input = "wdd";
        String padEnd = Strings.padEnd(input, minLenth, '0');
        System.out.println("后面补0：" + padEnd);
        String padStart = Strings.padStart(input, minLenth, '0');
        System.out.println("前面补0：" + padStart);
    }

    /**
     * 使用Splitter类来拆分字符串
     * <p>
     * Splitter类可以方便的根据正则表达式来拆分字符串，可以去掉拆分结果中的空串，可以对拆分后的字串做trim操作，还可以做二次拆分。
     */
    @Test
    public void TestString3() {
        Iterable<String> splitResults = Splitter.onPattern("[,，]{1,}")
                .trimResults()
                .omitEmptyStrings()
                .split("hello,word,,世界，               水平");
        System.out.println(splitResults);

        String toSplitString = "a=b;c=d,e=f";
        Map<String, String> kvs = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator('=').split(toSplitString);
        for (Map.Entry<String, String> entry : kvs.entrySet()) {
            System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }
    }

    @Test
    public void TestObject() {

    }

    @Test
    public void TestObjec1() {

    }
}

