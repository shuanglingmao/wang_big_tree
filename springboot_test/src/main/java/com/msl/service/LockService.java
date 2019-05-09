package com.msl.service;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/5/8 15:07
 */
public interface LockService {
    boolean modify(String id);

    Integer getCount(String id);

    boolean subCount(String id);
}
