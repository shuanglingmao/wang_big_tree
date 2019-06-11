package com.neo.service.impl;

import com.neo.mapper.UserMapper;
import com.neo.model.User;
import com.neo.service.UserService;
import com.neo.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/11
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        user.setPassWord(MD5Utils.getMD5String(user.getPassWord()));
        User userByNameAndPassword = userMapper.getUserByNameAndPassword(user);
        return userByNameAndPassword;
    }

    @Override
    public void register(User user) {
        user.setPassWord(MD5Utils.getMD5String(user.getPassWord()));
        userMapper.insert(user);
    }

    @Override
    public void augmentInfo(User user) {

    }

    @Override
    public void addVip(User user) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUserById(Integer userId) {
        return null;
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public Integer getLeavelById(Integer userId) {
        return null;
    }
}
