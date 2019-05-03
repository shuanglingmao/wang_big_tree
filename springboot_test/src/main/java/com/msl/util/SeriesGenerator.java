package com.msl.util;

/**
 * 序列号生成器 线程安全
 * 
 * @author dengfeng
 * @create_date:Jan 26, 201110:06:20 AM
 * @update_date:Jan 26, 201110:06:20 AM
 * @project_name:CARIS
 */
public class SeriesGenerator {

//    private static final AtomicCommands commans = RedisFactory.getClusterAtomicCommands(RedisConstans.OLD_GROUP_NAME);

    /**
     * redis生成序列号
     */
    public static String getConvenienceNO(String key, int length) {
//        String dateString = DateUtil.format(new Date(), "yyyyMMdd");
        String dateString = "20190314";
//        Long increment = commans.increment(RedisConstans.REDIS_NAMESPACE, key + dateString, 1);
//        if (1 == increment) {
//            commans.expire(RedisConstans.REDIS_NAMESPACE, key + dateString, 1, TimeUnit.DAYS);
//        }
        
//        String hitchSerialFlag = ConfCenterProperties.getInstance().getHitchSerialNo();
//        if (-1 != hitchSerialFlag.indexOf(dateString)){
//            increment += Long.valueOf(hitchSerialFlag.substring(8, hitchSerialFlag.length()));
//        }

        StringBuilder sb = new StringBuilder();
        sb.append(dateString);
//        for (int i = String.valueOf(increment).length(); i < length; i++) {
//            sb.append(0);
//        }
//        sb.append(increment);
        return "SFC" + sb.toString();
    }
}
