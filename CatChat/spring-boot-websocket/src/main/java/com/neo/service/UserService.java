package com.neo.service;

import com.neo.model.User;

import java.util.List;

/**
 * Description: 用户操作
 *
 * @author shuangling.mao
 * @date 2019/6/11 11:31
 */
public interface UserService {
    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 注册
     * @param user
     * @return
     */
    void register(User user);

    /**
     * 完善信息
     * @param user
     */
    void augmentInfo(User user);

    /**
     * 成为VIP
     * @param user
     */
    void addVip(User user);

    /**
     * 获取所有用户
     * @return
     */
    List<User> getAll();

    /**
     * 根据ID查询用户
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 根据姓名查询用户
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 获取用户等级
     * @param userId
     * @return
     */
    Integer getLeavelById(Integer userId);
}
