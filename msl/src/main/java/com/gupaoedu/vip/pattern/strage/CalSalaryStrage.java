package com.gupaoedu.vip.pattern.strage;

/**
 * 计算工资策略接口
 * 0-40个小时 每小时 200元
 * 0-50小时 超出40小时的 250元
 * 0-60小时 超出40小时的 250元  超出50小时的每小时300元
 */
public interface CalSalaryStrage {
    /**
     * 根据每周的工时计算工资
     * @param hours
     * @return
     */
    Double calSalary(Double hours);
}
