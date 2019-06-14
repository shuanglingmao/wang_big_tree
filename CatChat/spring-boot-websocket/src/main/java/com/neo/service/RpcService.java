package com.neo.service;

import com.neo.annotation.AvoidRepeatableCommit;
import com.neo.model.Result;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

/**
 * Description: 模拟远程服务
 *
 * @author shuangling.mao
 * @date 2019/6/14 11:02
 */
@Service("prcService")
public class RpcService {

    @AvoidRepeatableCommit
    public Result<Boolean> getFlag(String parm) {
        if (Strings.isNotBlank(parm)) {
            return new Result<Boolean>(true,0,"");
        } else {
            return new Result<Boolean>(false,0,"");
        }
    }


}
