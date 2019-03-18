package aop.AopTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/config/applicationContext.xml");
        new Test().manyAdvices("a","b");
    }

    public String manyAdvices(String param1, String param2) {
        System.out.println("方法：manyAdvices");
        return param1 + " 、" + param2;
    }
}
