package com.gupaoedu.vip.pattern.strage;

/**
 * 第一种计算工资的策略  适用于 工时0-40
 */
@Hours(max = 40)
public class FirstCalTypeStrage implements CalSalaryStrage{

    @Override
    public Double calSalary(Double hours) {

        return hours*200;
    }
}
