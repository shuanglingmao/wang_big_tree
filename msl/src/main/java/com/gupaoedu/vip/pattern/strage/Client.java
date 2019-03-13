package com.gupaoedu.vip.pattern.strage;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 会计发工资
 */
public class Client {
    Employee wdd = new Employee("王呆呆", 40D);
    Employee msl = new Employee("毛双领", 50D);
    Employee xs = new Employee("许嵩", 70D);

    List<Employee> employees = Arrays.asList(wdd, msl, xs);
    /**
     * 原始的方式  有点难看  test2会用简单工厂模式改进一下
     */
    @Test
    public void test1() {
        CalSalaryStrage calSalaryStrage = null;
        for (Employee employee : employees) {
            if (employee.getHours() <= 40) {
                calSalaryStrage = new FirstCalTypeStrage();
                Double salary = calSalaryStrage.calSalary(employee.getHours());
                employee.setSalary(salary);
            } else if (employee.getHours() > 40 && employee.getHours() <= 50) {
                calSalaryStrage = new SecondCalTypeStrage();
                Double salary = calSalaryStrage.calSalary(employee.getHours());
                employee.setSalary(salary);
            } else if (employee.getHours() > 50) {
                calSalaryStrage = new ThirdCalTypeStrage();
                Double salary = calSalaryStrage.calSalary(employee.getHours());
                employee.setSalary(salary);
            }
        }
        System.out.println(JSONObject.toJSONString(employees));
    }

    /**
     * 虽然比test1情况好了一些，但是工厂中依然 存在大量if else 日后增加策略 需要继续写if else
     * 接下来通过test3对这一情况进行改进
     */
    @Test
    public void test2() {
        for (Employee employee : employees) {
            CalSalaryStrage calSalaryStrage = CalSalaryStargeFactory.getInstace().getCalSalaryStrage(employee.getHours());
            employee.setSalary(calSalaryStrage.calSalary(employee.getHours()));
        }
        System.out.println(JSONObject.toJSONString(employees));
    }

    /**
     * 增加策略时 只需策略类上增加相应的注解即可 无需改动工厂类
     */
    @Test
    public void test3() {
        for (Employee employee : employees) {
            CalSalaryStrage calSalaryStrage = CalSalaryStargeFactoryV2.getInstace().getCalSalaryStrage(employee.getHours());
            employee.setSalary(calSalaryStrage.calSalary(employee.getHours()));
        }
        System.out.println(JSONObject.toJSONString(employees));
    }
}
