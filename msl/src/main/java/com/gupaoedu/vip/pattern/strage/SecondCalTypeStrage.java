package com.gupaoedu.vip.pattern.strage;

/**
 * 第二种计算工资的策略  适用于 工时0-50
 */
@Hours(min = 40,max = 50)
public class SecondCalTypeStrage implements CalSalaryStrage {

    @Override
    public Double calSalary(Double hours) {
        if (!(hours > 40 && hours <= 50)) {
            throw new RuntimeException("输入的小时不合法！");
        }
        return 40*200 + (hours-40)*250;
    }
}
