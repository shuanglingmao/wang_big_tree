package util;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2018/6/19 16:19
 */

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * description:日期类型转换类
 *
 * @author 刘一博
 * @version V1.0
 * @date 2018/3/7
 */
public class DateUtil {

    public static final String SHORT_PATTERN = "yyyy-MM-dd";
    /** 精确到分钟级的日期时间(yyyy-MM-dd HH:mm) */
    public static final String MIDDLE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String LONG_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期字符串转换为Date类型，不能转换，则返回null
     *
     * @param dateStr
     * @return
     * @author shuangling.mao
     * @date 2018年3月8日 15:25:05
     */
    public static Date tryParse(String dateStr) {
        if (dateStr == null) {
            return null;
        }
        DateTime dt = null;
        int length = dateStr.length();
        if (length == 10) {
            dt = DateTime.parse(dateStr, DateTimeFormat.forPattern(SHORT_PATTERN));
        } else if (length == 16) {
            dt = DateTime.parse(dateStr, DateTimeFormat.forPattern(MIDDLE_PATTERN));
        } else if (length == 19) {
            dt = DateTime.parse(dateStr, DateTimeFormat.forPattern(LONG_PATTERN));
        }
        if (dt == null) {
            throw new RuntimeException("日期" + dateStr + "格式不正确！");
        }
        return dt.toDate();
    }

    /**
     * 日期转为字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String toString(Date date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        return formatter.print(new DateTime(date));
    }

    /**
     * 获取当前系统时间
     * @return
     */
    public static String getCurrentDateTime(){
        return getCurrentDateTime(LONG_PATTERN);
    }

    /**
     * 获取当前系统时间
     * @return
     */
    public static String getCurrentDateTime(String pattern){
        Calendar calendar = Calendar.getInstance();
        Date dateTime = calendar.getTime();
        String currentDateTime = toString(dateTime, pattern);
        return currentDateTime;
    }


    /**
     * 校验给定的时间是否在两个时间范围内（可精确到秒hh:mm:ss或hh:mm）
     *
     * @param time
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean isBetweenTime4S(String time, String beginTime, String endTime) {
        if (time == null || beginTime == null || endTime == null) {
            return true;
        }
        String time4Format = getTime4S(time);
        String beginTimeFormat = getTime4S(beginTime);
        String endTime4Format = getTime4S(endTime);
        if (time4Format.compareTo(beginTimeFormat) >= 0 && time4Format.compareTo(endTime4Format) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 校验给定的日期是否在两个日期范围内（yyyy-mm-dd）
     *
     * @param date
     * @param beginDate
     * @param endDate
     * @return
     */
    public static boolean isBetweenDate4d(String date, String beginDate, String endDate) {
        if (date == null || beginDate == null || endDate == null) {
            return true;
        }
        DateTime date4Format = DateTime.parse(date, DateTimeFormat.forPattern(SHORT_PATTERN));
        DateTime beginDate4Format = DateTime.parse(beginDate, DateTimeFormat.forPattern(SHORT_PATTERN));
        DateTime endDate4Format = DateTime.parse(endDate, DateTimeFormat.forPattern(SHORT_PATTERN));
        if (date4Format.getMillis() >= beginDate4Format.getMillis() &&
                date4Format.getMillis() <= endDate4Format.getMillis()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 转换hh:mm为hh:mm:ss格式的时间
     *
     * @param time
     * @return
     */
    public static String getTime4S(String time) {
        if (time != null) {
            String[] timeArray = time.split(":");
            if (timeArray.length == 2) {
                return time + ":00";
            } else {
                return time;
            }
        }
        return "";
    }

    /**
     * 返回两个时间相差的分钟数
     * @param date
     * @param date1
     * @return
     */
    public static Long dateDiffToMinute(String date, String date1) {
        Date date2 = tryParse(clearSecond(date));
        Date date3 = tryParse(clearSecond(date1));
        Long result = ((date2.getTime() - date3.getTime()) / 1000 / 60);
        return result;
    }

    /**
     * 时间去掉秒
     * @param date
     * @return
     */
    public static String clearSecond(String date) {
        String[] split = date.split(" ");
        String[] split1 = split[1].split(":");
        String result = split[0] + " " + split1[0] + ":" + split1[1] + ":00";
        return result;
    }

    /**
     * 返回年月日 yyyy-MM-dd
     */
    public static String StringOrDate2Ymd(Object date) {
        String ymd = null;
        if (date instanceof Date) {
            Date dateTime = (Date) date;
            ymd = toString(dateTime, SHORT_PATTERN);
        }
        if (date instanceof String) {
            Date date1 = tryParse((String) date);
            ymd = toString(date1, SHORT_PATTERN);
        }
        return ymd;
    }

    /**
     * 比较两个时间  第一个时间是否大于第二个时间  精确到秒
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean compareTimeBoolean(String startTime,String endTime) {
       /* //如果两个参数之一只有年月日
        if (startTime.length() == 10 || endTime.length() == 10) {
           return tryParse(startTime).getTime() < tryParse(endTime).getTime() ? false : true;
        }else if(startTime.length() == 19 || endTime.length() == 19) {
            return tryParse(startTime).getTime() < tryParse(endTime).getTime() ? false : true;
        } else {
            throw new BizException("日期格式不正确！");
        }*/
        return startTime.compareTo(endTime) > 0;
    }

    public static void main(String[] args) {
        String time1 = "2018-06-19 10";
        String time2 = "2018-06-19 13:00:00";
        int i = time1.compareTo(time2);
        System.out.println(i);

    }

    /**
     *
     * @Description:  年月日 日期+n天
     * @author shuangling.mao
     * @date 2018/6/7 18:44
     * @return
     */
    public static String ymdAddN(String ymd, int n) {
        String[] split = ymd.split("-");
        Integer day1 = Integer.valueOf(split[2]) + n;
        return split[0] + "-" + split[1] + "-" + day1;
    }
}
