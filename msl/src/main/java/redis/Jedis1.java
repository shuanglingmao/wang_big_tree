package redis;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.access.method.P;
import redis.clients.jedis.Jedis;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Jedis1 {
    Jedis jedis = null;
    public static void main(String[] args) {








    }

    @Before
    public void before() {
        jedis =  new Jedis("101.34.121.25", 6379);
        jedis.auth("jin1tian8");
    }

    @Test
    public void set() {
        //        jedis.set("aaa", "赵二狗");

        //将key值设置为value，并将设置key的生存周期
//        jedis.setex("aaa", 60, "李二蛋");

        //:只有当key不存在的情况下，将key设置为value；若key存在，不做任何操作，结果成功返回1，失败返回0
        long result = jedis.setnx("aaa", "赵四");

        System.out.println(result);

        System.out.println(jedis.get("aaa"));
    }

    @Test
    public void get() {
        JedisUtils.set("aaa", "123456", 1000);

        System.out.println(JedisUtils.get("aaa"));
    }

    @Test
    public void expire() {
        jedis.expire("aaa", 60);
        System.out.println(jedis.ttl("aaa"));
    }

    @Test
    public void list() {
        //l是left左边的意思   从左边拿出来一条数据
        jedis.lpush("list", "张三");
        jedis.lpush("list", "李四");
        jedis.lpush("list", "王五");
        jedis.lpush("list", "赵六");

        System.out.println(jedis.lpop("list"));

//        jedis.l
//        String result = jedis.lpop("key1");

//        System.out.println(result);
    }

    @Test
    public void setObj() {
        Dog dog = new Dog();
        dog.setAge(3);
        dog.setName("王十一");
        JedisUtils.set("dog", dog, 1000);
    }

    @Test
    public void getObj() {
        Dog dog = JedisUtils.get("dog", Dog.class);
        System.out.println(dog);
    }

    @Test
    public void test() {
        //执行业务逻辑,省略100行
        Dog dog = JedisUtils.get("dog", Dog.class);
        //返回Dog
        Result<Dog> result = new Result<>();
        result.setT(dog);
    }


    @Test
    public void selectData() throws InterruptedException {
        long begin = System.currentTimeMillis();

        //执行业务逻辑，查询数据
        Dog dog = this.getDog(1L);
        System.out.println(dog);

        long end = System.currentTimeMillis();
        long costTime = end - begin;

        System.out.println("执行完成，总共耗时：" + costTime);
    }

    private static final String DOG_PREFIX = "DOG_";

    @Test
    public void selectDate4Cache() throws InterruptedException {
//        long begin = System.currentTimeMillis();
//        Long id = 2L;
//        Dog dog = JedisUtils.get(DOG_PREFIX + id, Dog.class);
//        if (Objects.isNull(dog)) {
//            //如果缓存中没查到数据，则从数据库查询
//            dog = this.getDog();
//            //吧这个数据放到缓存中
//            JedisUtils.set(DOG_PREFIX + id, dog, 1000);
//        }
//        System.out.println(dog);
//        long end = System.currentTimeMillis();
//        long costTime = end - begin;
//
//        System.out.println("执行完成，总共耗时：" + costTime);
//        return dog;

    }

    @Test
    public void selectDate4Cache1() {
        Long id = 2L;
        System.out.println(JedisUtils.get4Cache(DOG_PREFIX + id, Dog.class, 120L, () -> getDog(id)));
    }

    public Dog getDog(Long id)  {
        //模拟一下 每次从数据库查询Dog都需要两秒钟
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Dog dog = new Dog();
        dog.setId(2L);
        dog.setAge(24);
        dog.setName("刘能");
        return dog;
    }

    @Test
    public void stream() {
        Dog dog1 = new Dog();
        dog1.setId(2L);
        dog1.setAge(24);
        dog1.setName("刘能");

        Dog dog2 = new Dog();
        dog2.setId(3L);
        dog2.setAge(35);
        dog2.setName("赵四");

        List<Dog> collect1 = Stream.of(dog1, dog2)
                .sorted(Comparator.comparing(Dog::getAge))
                .collect(Collectors.toList());

        List<Dog> collect = Stream.of(dog1, dog2)
                        .sorted(Comparator.comparing(Dog::getAge)).collect(Collectors.toList());

        System.out.println(collect.toString());


    }



}

@Data
class Dog {
    private Long id;

    private String name;

    private Integer age;
}
