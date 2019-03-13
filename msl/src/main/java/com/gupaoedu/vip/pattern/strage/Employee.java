package com.gupaoedu.vip.pattern.strage;

/**
 * 员工类
 */
public class Employee {
    private String name;
    private Double hours;
    private Double salary;

    public Employee(String name, Double hours) {
        this.name = name;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
