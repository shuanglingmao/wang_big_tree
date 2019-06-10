package com.neo.mapper;

import com.neo.enums.UserSexEnum;
import com.neo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Description:
 *
 * @author shuangling.mao
 * @date 2019/6/10 14:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testInsert() throws Exception {
        userMapper.insert(new User("aammx", "a123456", UserSexEnum.MAN));
        userMapper.insert(new User("bbmmx", "b123456", UserSexEnum.WOMAN));
        userMapper.insert(new User("ccmmx", "b123456", UserSexEnum.WOMAN));
    }

    @Test
    public void getAll() throws Exception {
        final List<User> all = userMapper.getAll();
        System.out.println(all.toString());
    }

    @Test
    public void getOne() throws Exception {
        System.out.println(userMapper.getOne(1L));
    }

    @Test
    public void insert() throws Exception {
        userMapper.insert(new User("毛双领", "123456", UserSexEnum.MAN));
    }

    @Test
    public void update() throws Exception {
        userMapper.update(new User("毛双领", "123456", UserSexEnum.MAN));
    }

    @Test
    public void delete() throws Exception {
        userMapper.delete(100L);
    }

    @Test
    public void testGetUserByCondition() throws Exception {
        final User user = new User();
        user.setUserName("毛双领");
        System.out.println(userMapper.getUserByCondition(user));
    }

}