package com.msl.service.impl;

import com.msl.mapper.LockDao;
import com.msl.service.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/5/8 15:08
 */
@Service
public class LockServiceImpl  implements LockService {
    @Autowired
    private LockDao lockDao;

    @Override
    public boolean modify(String id) {
        return lockDao.modify(id) == 1;
    }

    @Override
    public Integer getCount(String id) {
        return lockDao.getCount(id);
    }

    @Override
    public boolean subCount(String id) {
        return lockDao.subCount(id) == 1;
    }
}
