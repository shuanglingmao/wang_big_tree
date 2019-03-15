package com.msl.service;

import com.msl.annotation.Msl;
import org.springframework.stereotype.Service;

/**
 * Description: ${todo}
 *
 * @author shuangling.mao
 * @date 2019/3/15 10:07
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    @Msl
    public Double pay(Double mount) {
        System.out.println("价格计算完毕");
        return mount*mount;
    }
}
