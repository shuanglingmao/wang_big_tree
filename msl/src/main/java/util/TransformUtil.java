package util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Description:
 *
 * @author shuangling.mao
 * @date 2019/3/13 14:52
 */
@Slf4j
public class TransformUtil {

    /**
     * F 和 T 中的属性名必须一致
     * @param list
     * @param type
     * @param <F>
     * @param <T>
     * @return
     */
    public static <F,T> List<T> transFrom(List<F> list, final Class<T> type) {
        List<T> transform = Lists.transform(list, new Function<F, T>() {
            @Nullable
            @Override
            public T apply(@Nullable F f) {
                T t = null;
                try {
                    t = type.newInstance();
                    BeanUtils.copyProperties(f,t);
                } catch (Exception e) {
                    log.error("transFrom异常");
                }
                return t;
            }
        });
        return transform;
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(1);
        student.setName("二狗");
        student.setSex(true);
        Student student1 = new Student();
        student1.setAge(2);
        student1.setName("铁锤");
        student1.setSex(false);
        final List<People> people = transFrom(Lists.newArrayList(student, student1), People.class);
        System.out.println(JSONObject.toJSONString(people));
    }
    @Data
    static class Student{
        private String name;
        private Integer age;
        private Boolean sex;
    }
    @Data
    static class People{
        private String name;
        private Integer age;
        private Boolean sex;
        private String address;
    }
}
