import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

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
}
