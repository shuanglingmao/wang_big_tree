package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 正则表达式util
 *
 * @author shuangling.mao
 * @date 2018/6/19 19:03
 */
public class RegexUtil {
    /**
     * 校验手机号格式是否 正确
     * @param mobile
     * @return
     */
    public static boolean isMobileForAssure(String mobile){
        String pattern = "((\\(\\d{3}\\))|(\\d{3}\\-))?13[0-9]\\d{8}$|15[0-9]\\d{8}$|17[0-9]\\d{8}$|18[0-9]\\d{8}$|14[0-9]\\d{8}$|166\\d{8}$|165\\d{8}$|19[8-9]\\d{8}";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(mobile);
        return matcher.find();
    }

}

