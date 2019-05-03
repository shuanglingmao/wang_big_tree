import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import util.FilterUtils;

import java.util.*;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2018/6/14 17:52
 */
public class HelloWord {
    public static void main(String[] args) {
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring-mybatis.xml"));
        System.out.println("HelloWord!");
    }

    @Test
    public void test1() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("list过滤前-->"+list.toString());

        //过滤出大于5的元素
        FilterUtils.filter(list, (integer) -> integer > 5);

        System.out.println("list过滤后-->"+list.toString());

    }

    @Test
    public void test2() {
        Set<String> set = new HashSet(){{
           add("赵二狗");
           add("李二蛋");
           add("王老七");
           add("谢广坤");
           add("刘能");
        }};

        System.out.println("set过滤前-->"+set.toString());

        //把谢广坤踢出去
        FilterUtils.filter(set, (name) -> name.equals("谢广坤"));
        System.out.println("set过滤后-->"+set.toString());
    }

    @Test
    public void test3() {
        Map<Integer,String> map = new HashMap(){{
            put(1,"a");
            put(2,"b");
            put(3,"c");
            put(4,"d");
            put(5,"e");
        }};
        System.out.println("map过滤前-->"+map.toString());
        FilterUtils.filter(map, new FilterUtils.Filter<Integer>() {
            @Override
            public boolean execute(Integer integer) {
                return integer % 2 == 0 ;
            }
        });
        System.out.println("map过滤后-->"+map.toString());

    }

}
