package com.neo.controller;

import com.neo.model.User;
import com.neo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/6/11
 */
@Controller
@RequestMapping("/user")
public class UserController{

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Boolean login(User user, HttpServletRequest request){
        User login = null;
        try{
            login = userService.login(user);
            request.getSession().setAttribute("user",login);
        }catch (Exception e){
           logger.error("登陆异常",e);
        }
        return login != null;
    }

    @RequestMapping("/register")
    @ResponseBody
    public Boolean register(User user){
        try{
            userService.register(user);
        } catch (Exception e){
            logger.error("注册异常",e);
            return false;
        }
        return true;
    }

}
