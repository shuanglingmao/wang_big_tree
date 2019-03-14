package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/3/14 16:58
 */
public class JsonUtil {
    public static String transferJson(Object args) {
        return transferJson(new Object[]{args});
    }

    public static String transferJson(Object[] args) {
        return transferJson(args, null);
    }

    private static String transferJson(Object[] args, final Object retValue) {
        if (args == null) {
            args = new Object[]{};
        }
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("input", args);
        return transferJson(JSONObject.toJSONString(map), retValue);
    }

    private static String transferJson(String jsonArgs, final Object retValue) {
        Map<String, Object> map = new HashMap<String, Object>(1);
        if (retValue != null) {
//            if (retValue instanceof RemoteResultVO) {
//                map.put("output", ((RemoteResultVO) retValue).getResult());
//            } else {
//                map.put("output", retValue);
//            }
        }
        if (map.isEmpty()) {
            return jsonArgs;
        } else {
            return jsonArgs + " " + JSON.toJSONString(map);
        }
    }
}
