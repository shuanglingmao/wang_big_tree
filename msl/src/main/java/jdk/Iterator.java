package jdk;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description: 迭代器
 *
 * @author shuangling.mao
 * @date 2018/6/14 18:04
 */
public class Iterator {
    public static void main(String[] args) {
        List<String> strList = Arrays.asList("张三","李四","王五","赵六","田七");

        Set<Integer> intSet = new HashSet<Integer>();
        intSet.add(1);
        intSet.add(2);
        intSet.add(3);

        //迭代器遍历list
        java.util.Iterator<Integer> iterator = intSet.iterator();
        while (iterator.hasNext()) {
            Integer number = iterator.next();
            System.out.println(number);
        }
    }
}
