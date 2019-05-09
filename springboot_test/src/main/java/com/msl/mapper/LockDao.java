package com.msl.mapper;

import org.springframework.stereotype.Repository;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/5/8 14:01
 */
@Repository
public interface LockDao {
    Integer modify(String id);

    Integer getCount(String id);

    Integer subCount(String id);
}
