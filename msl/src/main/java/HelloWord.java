import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.math.IntMath;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2018/6/14 17:52
 */
public class HelloWord {
    public static void main(String[] args) {
        final List list = new ArrayList<>();

        new HelloWord().test1(list);
        System.out.println(list.size());

        new HelloWord().test2(list);
        System.out.println(list.size());
    }

    void test1(List list) {
        list = null;
    }

    void test2(List list) {
        list.add(1);
    }

    @Test
    public void test1() {
        ArrayList<Integer> list = new ArrayList<>(50);
        for (int i = 0; i < 30; i++) {
            list.add(i);
        }
        list.trimToSize();
        System.out.println(list.size());
    }

    @Test
    public void test2() {
//        System.out.println(16>>1);
          //  16 % 2
//        System.out.println(16&2);
//        System.out.println(3%2);
//        System.out.println(3&1);


        String a = "str";
        String b = "str";
        System.out.println(a==b);

        String c= new String ("str");
        String d = new String("str");
        System.out.println(c==d);

        float f = 0.8f;
        System.out.println(f == 0.1*8);
    }
    //1.d 2.c
    @Test
    public void mod() {
        Data data31 = new Data(31,"name31",null);
        Data data32 = new Data(32,"name32",null);
        Data data22 = new Data(22,"name22",null);

        Data data21 = new Data(21,"name21",Lists.newArrayList(data31,data32));
        Data parent = new Data(1,"name1", Lists.newArrayList(data21,data22));
        System.out.println(JSONObject.toJSONString(parent));


        print(parent);


    }

    private void print(Data data) {
        System.out.println(data.getName());
        if (data.child != null) {
            for (Data children : data.child) {
                print(children);
            }
        }
    }

    @lombok.Getter
    class Data {
        private int id;
        private String name;
        private List<Data> child;

        public Data(int id, String name, List<Data> child) {
            this.id = id;
            this.name = name;
            this.child = child;
        }
    }


    @Test
    public void test3() {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
        threadLocal.remove();
        threadLocal.set(1);
        threadLocal1.set(2);
        System.out.println(threadLocal.get());
        System.out.println(threadLocal1.get());

        System.out.println(Objects.equal(1, 2));
    }

    @Test
    public void test4() {
        String parm = "{\"uid\":\"a325c4dd-3d74-48cd-9b4c-11705e529d9e\",\"inspectType\":1,\"vehicleId\":225984,\"imei\":\"c59cea7171229e05\",\"xid\":\"35757\",\"deptId\":\"20\",\"orderId\":\"37013967\",\"inspectId\":\"\",\"key\":\"\",\"version\":\"3.1.3\"}";
        final Integer xid = getValueByKey(parm, "xid", Integer.class);
        System.out.println(xid);
    }
    public <T> T getValueByKey(String parm,String key,Class<T> type) {
        final JSONObject jsonObject = JSONObject.parseObject(parm);
        final Set<String> keys = jsonObject.keySet();
        if (!keys.contains(key)) {
            System.out.println("key:"+key+"在JSON中不存在");
            return null;
        }
        final Object o = jsonObject.get(key);
        if (!Objects.equal(o.getClass().getName(),type.getName())) {
            System.out.println("传入的类型与实际类型不匹配,请检查");
        }
        return (T) o;
    }

}
