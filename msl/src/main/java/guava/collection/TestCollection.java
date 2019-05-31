package guava.collection;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.*;

/**
 * Description: 瓜娃子工具类 之  集合
 *
 * The Guava API provides very useful new collection types that work very nicely with existing java collections.
 * guava API 提供了有用的新的集合类型，协同已经存在的java集合工作的很好。
 * @author shuangling.mao
 * @date 2019/3/25 18:32
 */
public class TestCollection {


    /**
     * 集合的创建
     */
    @Test
    public void testCreat() {
        //之前的写法
        Map<String,Map<String,String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();

        //瓜娃子写法  等号后面不需要加泛型 jdk优化后已无差别
        Map<String,Map<String,String>> map1 = Maps.newHashMap();
        List<String> list1 = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();
    }

    /**
     * 集合的初始化
     */
    @Test
    public void testInit() {
        //之前的写法
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        //或者双括号语法
        List list1 = new ArrayList(){{
            add("赵二狗");
            add("李二蛋");
            add("王二姐");
        }};
//        System.out.println(list1);

        //asList返回的List是Array中的实现的内部类,而该类并没有定义add和remove方法.
        List list3 = Arrays.asList(1,2,3);
        //执行add  和 remove 方法会报错  java.lang.UnsupportedOperationException
//        System.out.println(list3.add(4));


        //TODO  瓜娃子写法
        List list2 = Lists.newArrayList("张三","李四","王五");
        Map<Integer,Dog> map = ImmutableMap.of(1,new Dog(1,"大黄", 2, true),2,new Dog(2,"黑豆", 4, true));
        System.out.println(map);

    }


    /**
     *  一种key可以重复的map，子类有ListMultimap和SetMultimap，对应的通过key分别得到list和set
     */
    @Test
    public void testMultiMap() {
        final Dog dog1 = new Dog(1,"大黄", 2, true);
        final Dog dog2 = new Dog(2,"黑豆", 4, true);
        final Dog dog3 = new Dog(3,"大黄", 3, false);

        final ArrayListMultimap<Object, Object> multimap = ArrayListMultimap.create();
        multimap.put(dog1.getName(),dog1);
        multimap.put(dog2.getName(),dog2);
        multimap.put(dog3.getName(),dog3);

        final List<Object> list = multimap.get("大黄");
        System.out.println("key为大黄===>"+JSONObject.toJSONString(list));
//        [{"age":2,"gender":true,"name":"大黄"},{"age":3,"gender":false,"name":"大黄"}]

        final ArrayListMultimap<Object, Object> multimap1 = ArrayListMultimap.create();
        multimap1.put(1,"a");
        multimap1.put(2,"b");
        multimap1.put(3,"c");
        multimap1.put(2,"d");
        multimap1.put(2,"e");
        final List<Object> get3 = multimap1.get(3);
        System.out.println("get3==>"+get3);
        final List<Object> get2 = multimap1.get(2);
        System.out.println("get3==>"+get2);
    }


    /**
     * 不是集合，可以增加重复的元素，并且可以统计出重复元素的个数，
     */
    @Test
    public void testMultiSet() {
        Multiset<Integer> multiset = HashMultiset.create();
        multiset.add(1);
        multiset.add(2);
        multiset.add(2);
        multiset.add(2);
        multiset.add(3);
        System.out.println("multiset的长度===>"+multiset.size());
        System.out.println("multiset中为2的有几个===>"+multiset.count(2));

//        final Set<Multiset.Entry<Integer>> entries = multiset.entrySet();
//        System.out.println(entries);
        //[1, 2 x 3, 3]

        //[1,2,3]  获得真正不重复的set
        final Set<Integer> integers = multiset.elementSet();
        for (Integer integer : integers) {
            System.out.println(integer+"有:"+multiset.count(integer)+"个");
        }
//        multiset.setCount(2,6);

    }



    /**
     * 有两个Key的Map
     */
    @Test
    public void testTable() {
        Table<Integer,String,Dog> table = HashBasedTable.create();
        table.put(1,"大黄",new Dog(1,"大黄", 2, true));
        table.put(2,"小黑",new Dog(2,"小黑", 1, false));
        System.out.println(table);
        //{1={大黄=TestCollection.Dog(name=大黄, age=2, gender=true)}, 2={小黑=TestCollection.Dog(name=小黑, age=1, gender=false)}}
        final Set<Table.Cell<Integer, String, Dog>> cells = table.cellSet();
        for (Table.Cell<Integer, String, Dog> cell : cells) {
            System.out.println(cell.getRowKey());
            System.out.println(cell.getColumnKey());
            System.out.println(cell.getValue());
        }
        System.out.println(cells);
    }

    @Test
    public void testTable1() {
        Table<Integer,Integer,Dog> dogTable = HashBasedTable.create();
        dogTable.put(1,1,new Dog(1, "大黑", 1, true));
        dogTable.put(1,2,new Dog(2, "二黑", 2, true));
        dogTable.put(2,1,new Dog(3, "三黑", 3, true));
        dogTable.put(2,2,new Dog(4, "四黑", 3, false));
        dogTable.put(5,27,new Dog(5, "五黑", 7, false));
        dogTable.put(6,29,new Dog(6, "六黑", 19,false));
        dogTable.put(7,33,new Dog(7, "七黑", 12,false));
        dogTable.put(8,66,new Dog(8, "小黑", 11,false));

        // 如果rowKey 和columnKey 一样 新来的会被覆盖掉
        final Dog dog = dogTable.get(2, 20);
        //获取row为1的 元素
        final Map<Integer, Dog> rowMap = dogTable.row(1);
        //{1=TestCollection.Dog(id=1, name=大黑, age=1, gender=true), 2=TestCollection.Dog(id=2, name=二黑, age=2, gender=true)}
        System.out.println(rowMap);
        final Integer max = Collections.max(rowMap.keySet());
        System.out.println(max);
    }















    @Data
    @AllArgsConstructor
    class Dog {
        private Integer id;
        private String name;
        private Integer age;
        private Boolean gender;

    }
}
