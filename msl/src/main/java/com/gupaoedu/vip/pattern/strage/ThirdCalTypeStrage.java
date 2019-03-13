package com.gupaoedu.vip.pattern.strage;

/**
 * 第三种计算工资的策略  适用于 工时0-60或更多
 */
@Hours(min = 60)
public class ThirdCalTypeStrage implements CalSalaryStrage {

    @Override
    public Double calSalary(Double hours) {
        if (hours <= 50) {
            throw new RuntimeException("输入的小时不合法！");
        }
        return 40*200 + 10*250 + (hours-50)*300;
    }
}
