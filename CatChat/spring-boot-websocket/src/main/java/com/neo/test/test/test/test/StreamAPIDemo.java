package com.neo.test.test.test.test;

import com.google.common.collect.Lists;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.neo.model.User;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description:
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/6/24 0024
 * @Author 毛双领 <shuangling.mao>
 */
public class StreamAPIDemo {
    List<Integer> list = new ArrayList<Integer>() {{
        add(1);add(2);add(3);add(4);add(5);add(5);
    }};
    @Test
    public void test1() {
        //实例化
        //1 通过集合
        //default Stream<E> stream()  返回一个顺序流
        Stream<Integer> stream = list.stream();
        //default Stream<E> parallelStream()  返回一个并行流
        Stream<Integer> parallelStream = list.parallelStream();

        //2 通过数组
        IntStream stream1 = Arrays.stream(new int[]{1, 2, 3, 4, 5});

        //3 通过Stream本身 of()
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5);
        Stream<Object> build = Stream.builder().add(1).add(2).build();

        //4 通过Stream 创建无限流
        //迭代 public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        //遍历前十个偶数
        Stream<Integer> iterate = Stream.iterate(0, t -> t + 2).limit(10);
        iterate.forEach(System.out :: println);
        //生成 造数据
        //public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math :: random);
    }


    @Test
    public void test2() {
        //筛选与切片
        //filter(Predicate p)   排除
        Stream<Integer> stream = list.stream();
        //查询大于2的元素
        stream.filter(integer -> integer > 2)
                .forEach(System.out :: println);

        //limit(n)截断，使元素数量不能超过给定数量
        Stream<Integer> stream1 = list.stream();
        //前两条数据
        stream1.limit(2).forEach(System.out :: println);

        //skip(n) 截断  跳过元素,返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流
        stream1.limit(3).forEach(System.out :: println);

        //distinct() 筛选，通过流所生成元素的hashCode()和equals()去重
        Stream<Integer> stream2 = list.stream();
        stream2.distinct().forEach(System.out :: println);

    }

    @Test
    public void test3() {
        //map(Function f) 将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并
        //小写转大写
        Stream<String> stream = Arrays.asList("aa", "bb", "cc", "dd", "ee").stream();
        stream.map(str -> str.toUpperCase()).forEach(System.out :: println);

        //获取姓名长度大于3的用户姓名
        List<User> list = new ArrayList<User>();
        list.stream().map(User :: getUserName)
                .filter(name -> name.length() > 3)
                .forEach(System.out :: println);

        //flatMap(Function f)  将流中的每个值都换成另一个流，然后吧所有流连接成一个流
        //map相当于 list.add(list)    flatMap相当于 list.addAll(list)

    }

    @Test
    public void test4() {
        //排序
        //sorted()   产生一个新流，其中按自然顺序排序
        Stream<String> stream = Arrays.asList("gg", "ff", "zz", "cc", "aa","bb").stream();
        stream.sorted().forEach(System.out :: println);

        //sorted(Comparator com) 产生一个新流，其中按比较器顺序排序
        Stream<String> stream1 = Arrays.asList("gg", "ff", "zz", "cc", "aa","bb").stream();
        //自定义倒序排序
        stream1.sorted(String::compareTo).forEach(System.out :: println);
        //泛型如果是实体类 需要实现Comparable接口
    }

    @Test
    public void test5() {
        //终止操作
        List<User> list = new ArrayList<>();

        //匹配与查找
        //allMatch(Predicate p)  检查是否匹配所有元素
        //是否所有的用户都大于18
        boolean b = list.stream().allMatch(user -> user.getAge() > 18);
        //anyMatch(Predicate p)  检查是否至少匹配一个元素
        //是否存在用户 住在豆各庄
        boolean dgz = list.stream().anyMatch(user -> user.getAddress().contains("豆各庄"));

        //noneMatch(Predicate p) 检查是否没有匹配的元素
        //是否存在姓 狗的人
        boolean dog = list.stream().noneMatch(user -> user.getUserName().startsWith("狗"));
        //findFirst返回第一个元素
        Optional<User> first = list.stream().findFirst();
        User user = first.get();
        //findAny 返回当前流中任意元素   并行流 findAny才会任意一个
        User user1 = list.stream().findAny().get();
        //count 返回流中元素总个数
        //max(Comparator c) 返回流中最大值
        //工资大于 一万的用户个数
        long count = list.stream().filter(user2 -> user.getSalary() > 1000).count();
        //返回最高的工资
        final Optional<Double> maxSalary = list.stream().map(User::getSalary).max(Double::compare);
        //forEach(Consumer c) 内部迭代
    }

    @Test
    public void test6() {
        //终止操作
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<User> userList = new ArrayList<>();
        //规约
        //reduce(T identity,BinaryOperator b)  可以将流中元素反复结合起来，得到一个值返回T
        //计算1-10的和     0初始值
        System.out.println(list.stream().reduce(0, Integer::sum));
        // reduce(BinaryOperator b) 将六中的元素反复结合起来，得到一个值 返回Optional<T>
        //计算公司所有员工 工资总和
        Stream<Double> salaryStream = userList.stream().map(User::getSalary);

        final Optional<Double> amount = salaryStream.reduce(Double::sum);

//        final Optional<Double> amount1 = salaryStream.reduce(new BinaryOperator<Double>() {
//            @Override
//            public Double apply(Double aDouble, Double aDouble2) {
//                return Double.sum(aDouble,aDouble2);
//            }
//        });
//        salaryStream.reduce((aDouble, aDouble2) -> Double.sum(aDouble,aDouble2));
    }

    @Test
    public void test7() {
        //终止操作
        List<User> list = new ArrayList<>();
        //收集 collect(Collector c) 将流转换为其他形式。接收一个Collector接口的实现,用于给Stream中元素做汇总的方法
        //Collector接口中方法的实现决定了如何对流执行收集器的操作(如收集到List、Set、Map)
        //另外，Collectors 类提供了很多静态方法，可以方便的创建收集器实例

        //查找工资大于1000玩的员工结果返回成一个List  Set
        final List<User> listResult = list.stream().filter(user -> user.getSalary() > 10000)
                .collect(Collectors.toList());
        final Set<User> setResult = list.stream().filter(user -> user.getSalary() > 1000)
                .collect(Collectors.toSet());
        //其他的自己看文档


    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Dog {
        private String name;
        private Cat cat;

        public Dog(String name) {
            this.name = name;
        }
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Cat {
        private String name;
    }
    @Test
    public void test8() {
        //不能传null
        Optional<Dog> dog = Optional.of(new Dog());
        System.out.println(dog);
        //可以为空
        final Optional<Object> o = Optional.ofNullable(null);
        System.out.println(o);
    }

    @Test
    public void test9() {
        Dog dog = new Dog();
        dog.setName("二狗");
        //空指针
//        System.out.println(getCatName(dog));
        //无空指针 校验
        System.out.println(getCatName2(null));
    }

    public String getCatName(Dog dog) {
        //存在空指针风险
        return dog.getCat().getName();
    }
    public String getCantName1(Dog dog) {
        if (dog != null) {
            Cat cat = dog.getCat();
            if (cat != null) {
                return cat.getName();
            }
        }
        return null;
    }
    //Optional
    public String getCatName2(Dog dog) {
        Optional<Dog> optional = Optional.ofNullable(dog);
        //此时dog1 一定非空
        Dog dog1 = optional.orElse(new Dog("二狗"));
        Cat cat = dog1.getCat();

        Optional<Cat> optional1 = Optional.ofNullable(cat);
        //如果 optional1.get()为空  就拿到 new Cat("小白")   备胎
        Cat cat1 = optional1.orElse(new Cat("小白"));
        return cat1.getName();
    }


    @Test
    public void testCollect() {
        List<Person> personlist = Lists.newArrayList(new Person(1,"1"),new Person(2,"2"),
                new Person(3,"3"),new Person(4,"4"),new Person(5,"5"));


        ArrayList<Man> collect = personlist.stream().collect(() -> new ArrayList<Man>(), (list, person) -> {
            list.add(new Man(person.getId(), person.getName()));
        }, (m,n)->{});

        ArrayList<Man> collect1 = personlist.stream().collect(ArrayList::new, (list, person) -> {
            list.add(new Man(person.getId(), person.getName()));
        }, (m,n)->{});


        collect.forEach(System.out :: println);


        List<Person> result = Lists.transform(personlist, person -> {
            final Person person1 = new Person();
            person1.setId(person.getId());
            person1.setName(person.getName());
            return person;
        });

    }
    @Test
    public void test10() {
        List<Person> personlist = Lists.newArrayList(new Person(1,"1"),new Person(2,"2"),
                new Person(3,"3"),new Person(4,"4"),new Person(5,"5"),
                new Person(5,"5"),new Person(5,"5"),new Person(1,"3"),new Person(1,"9"),
                new Person(1,"asdasd"),new Person(1,"哈哈哈"));
        Map<Integer, List<Person>> collect = personlist.stream().collect(Collectors.groupingBy(Person::getId));
        System.out.println(collect.get(1).toString());
    }


    @Test
    public void testCollect1() {
        List<TaxiDimAppraisal> taxiDimAppraisals  = Lists.newArrayList(new TaxiDimAppraisal("aa", "aa"), new TaxiDimAppraisal("bb", "bb"));

        List<AppraiseScoreDTO> appraiseScoreDTOList  = taxiDimAppraisals.stream().collect(ArrayList::new, (list, taxi) -> {
            list.add(AppraiseScoreDTO.builder().name(taxi.getName()).score(taxi.getScore()).build());
        }, (m, n) -> {});


        appraiseScoreDTOList.forEach(System.out :: println);
    }

}







@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class Person {
    private Integer id;
    private String name;
}

@Data
@ToString
@AllArgsConstructor
class Man {
    private Integer id;
    private String name;
}
@Data
@Builder
@ToString
class TaxiDimAppraisal {
    private String name;
    private String score;
}

@Data
@Builder
@ToString
class AppraiseScoreDTO  {
    private String name;
    private String score;
}
