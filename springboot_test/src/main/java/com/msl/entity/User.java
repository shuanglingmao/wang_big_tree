package com.msl.entity;




import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/3/18 11:16
 */
public class User implements Serializable{

    private String name;
    private CnnetGlobalConfigBean cnnetGlobalConfigBean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CnnetGlobalConfigBean getCnnetGlobalConfigBean() {
        return cnnetGlobalConfigBean;
    }

    public void setCnnetGlobalConfigBean(CnnetGlobalConfigBean cnnetGlobalConfigBean) {
        this.cnnetGlobalConfigBean = cnnetGlobalConfigBean;
    }

    public User() {

    }



    public static void main(String[] args) {
        final User user = new User();
        String s = "{\"cnnetId\":14,\"cnnetName\":\"国家电网\",\"cnnetPartnerId\":32}";
        try {
            BeanUtils.setProperty(user,"name","二狗");
            BeanUtils.setProperty(user,"cnnetGlobalConfigBean",s);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }
    public static class CnnetGlobalConfigBean implements Serializable{
        private static final long serialVersionUID = 1L;
        private Long cnnetId;
        private String cnnetName;
        private Integer cnnetPartnerId;

        public Long getCnnetId() {
            return cnnetId;
        }

        public void setCnnetId(Long cnnetId) {
            this.cnnetId = cnnetId;
        }

        public String getCnnetName() {
            return cnnetName;
        }

        public void setCnnetName(String cnnetName) {
            this.cnnetName = cnnetName;
        }

        public Integer getCnnetPartnerId() {
            return cnnetPartnerId;
        }

        public void setCnnetPartnerId(Integer cnnetPartnerId) {
            this.cnnetPartnerId = cnnetPartnerId;
        }
    }

    /**
     * Joiner
     */
    @Test
    public void test() {
        //可以过滤空操作   1，2，3，5
        System.out.println(Joiner.on(",").skipNulls().join(new ArrayList<Integer>(5){{add(1);add(2);add(3);add(null);add(5);}}));
        //如果有元素为空会报空指针
        System.out.println(Joiner.on(",").join(new ArrayList<Integer>(5){{add(1);add(2);add(3);add(null);add(5);}}));
        //如果你想替换为 null的字符串 你可以这么做     1,2,3,value is null,5
        System.out.println(Joiner.on(",").useForNull("value is null").join(new ArrayList<Integer>(5){{add(1);add(2);add(3);add(null);add(5);}}));
    }

    /**
     * Splitter
     */
    @Test
    public void testSplitter() {
        String s = "1,2,3";
        final Iterable<String> split = Splitter.on(s).split(",");
        final ArrayList<String> list = Lists.newArrayList(split);
        System.out.println(JSONObject.toJSONString(list));

    }

    @Test
    public void test1() {
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6);
        List<String> transform = Lists.transform(list, new Function<Integer, String>() {
            @Override
            public String apply(Integer input) {
                return input + "~";
            }
        });
        System.out.println("list:"+list);
        System.out.println("transform:"+transform);

        System.out.println("~~~~~~~~");
        //修改list的值
        list.add(7);
        System.out.println("transform:"+transform);
    }

    @Test
    public void test2() {
        //验证手机号码格式是否正确
        String mobile = "18612800162";
        Pattern p = Pattern.compile("^13[0-9]\\\\d{8}$|^14[0-9]\\\\d{8}$|^15[0-9]\\\\d{8}$|^17[0-9]\\\\d{8}$|^18[0-9]\\\\d{8}$|^16[5,6]\\\\d{8}$|^19[1,8,9]\\\\d{8}$");
        Matcher m = p.matcher(mobile);
        System.out.println(m.matches());

    }

}
