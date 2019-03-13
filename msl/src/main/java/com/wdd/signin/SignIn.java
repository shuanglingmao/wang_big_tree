package com.wdd.signin;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SignIn {
    private List<Integer> list = null;
    private SimpleDateFormat df = new SimpleDateFormat("dd");
    //  有一个长度为 30 的数组   用户签到后就在指定的位置添加 当前日期
    private void init() {
        list = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10,11,12,null,null,null);
    }


    /**
     * 签到
     */
    private void signIn() {
        //获取当前 today 是多少号   以(today-1)为下标  today位置放进list
        Integer today = Integer.valueOf(df.format(new Date()));
        list.add(today-1,today);
    }

    /**
     * 补签
     * @param today
     */
    private void remedy(Integer today) {
        //加个校验意思一下
        if (today < 0 && today > 30){
            System.out.println("非法参数");
            return;
        }
        if (list.get(today-1) != null) {
            System.out.println("这天已签过到");
            return;
        }
        list.add(today-1,today);
    }

    /**
     * 计算连续签到天数
     */
    private Integer calContinuousNum() {
        Integer result = 0;
        //获取今天几号
        Integer today = Integer.valueOf(df.format(new Date()));
        //如果今天签过到  连续天数从今天开始算
        if (list.get(today-1) != null) {
            return cal(today-1,result);
        } else {//如果今天未签到 连续天数从昨天开始算
            return cal(today-2,result);
        }
    }

    private Integer cal(Integer index,Integer result) {
        System.out.println("入参,index:"+index+"result:"+result);
        //避免下标越界
        if (index == 0) {
            if (list.get(0) == null) {
                return result;
            } else {
                return result+1;
            }
        }
        //如果前一天 签到了那么 递归调用此方法
        if (list.get(index) != null) {
            result++;
            result = cal(index-1,result);
        }
        return result;

    }


    public void add(Integer a) {
        a++;
    }
    @Test
    public void test() {
        init();
        Integer integer = calContinuousNum();
        System.out.println(integer);
//        System.out.println("------------");
//        System.out.println(cal(0,11));
    Integer a = 10;
    add(a);
    System.out.println(a);

    }

}
