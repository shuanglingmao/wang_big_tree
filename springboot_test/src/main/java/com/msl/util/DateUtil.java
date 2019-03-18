package com.msl.util;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期辅助类
 * Created by qilei on 2015/3/9.
 * Modified by lixin 2016/8/16
 */
public class DateUtil {

    public static final String LONG_PARRERN = "yyyy-MM-dd HH:mm:ss";
    public static final String MIDDLE_PARRERN = "yyyy-MM-dd HH:mm";
    public static final String SHORT_PARRERN = "yyyy-MM-dd";
	public static final String TIME_PATTERN = "HH:mm:ss";
   

    


	/**
	 *
	 * @param theDay 被计算的时间
	 * @param addDay 增加的天数
	 * @return
	 */
	public static Date addDay(Date theDay, int addDay){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(theDay);
		calendar.add(Calendar.DATE, addDay);
		return calendar.getTime();
	}

	public static void main(String[] args) {
		System.out.println((addDay(new Date(),31)));
	}




    

    


	private static Integer getCompareResult(Date dateTimeA, Date dateTimeB) {
		if(dateTimeA.getTime() > dateTimeB.getTime()){
            return 1;
        }else if(dateTimeA.getTime() < dateTimeB.getTime()){
            return -1;
        }else{
            return 0;
        }
	}

	/**
	 * 比较日期时间  不抛出错误  为空返回null
	 * @param dateTimeA
	 * @param dateTimeB
	 * @return
	 */
	public static Integer compareDateTime(Date dateTimeA, Date dateTimeB) {
		if(dateTimeA != null && dateTimeB != null){
			return getCompareResult(dateTimeA, dateTimeB);
		}else{
			return null;
		}
	}

    /**
     * @Description:增加分钟数
     * @param:date,i
     * @return:
     * @Date: 2017/10/16 14:26 created by 邢利超(lichao.xing@ucarinc.com)
     */
	public static Date addMinute(Date date, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, i);
		date = cal.getTime();
		return date;
	}

	/**
	 * 两个时间段是否有交集
	 * @param leftStartDate 第一个时间段开始日期 notNull
	 * @param leftEndDate 第一个时间段结束日期 notNull
	 * @param rightStartDate 第二个时间段开始日期 notNull
	 * @param rightEndDate 第二个时间段结束日期 notNull
	 * @return
	 * @author yongjun.ji
	 * @date 2018/9/11
	 */
	public static boolean isOverlap(Date leftStartDate, Date leftEndDate, Date rightStartDate, Date rightEndDate) {
		return rightStartDate.getTime() <= leftEndDate.getTime()
				&& rightEndDate.getTime() >= leftStartDate.getTime();
	}

}
