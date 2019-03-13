package com.gupaoedu.vip.pattern.strage;

public class CalSalaryStargeFactory {
    /**单例*/
    private CalSalaryStargeFactory(){}
    private static class CalSalaryStargeFactoryInstance{
        private static CalSalaryStargeFactory instance = new CalSalaryStargeFactory();
    }
    public static CalSalaryStargeFactory getInstace() {
        return CalSalaryStargeFactoryInstance.instance;
    }

    public CalSalaryStrage getCalSalaryStrage(Double hours) {
        if (hours <= 40) {
            return new FirstCalTypeStrage();
        } else if (hours > 40 && hours <= 50) {
            return new SecondCalTypeStrage();
        } else if (hours > 50) {
            return new ThirdCalTypeStrage();
        }

        throw new RuntimeException("没有找到合适的策略！");

    }
}
