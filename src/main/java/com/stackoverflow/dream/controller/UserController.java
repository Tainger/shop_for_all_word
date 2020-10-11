package com.stackoverflow.dream.controller;

import com.stackoverflow.dream.pojo.User;
import com.stackoverflow.dream.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author rocky
 * @ClassName UserController
 * @Description
 * @Create by rocky
 * @Date 2020/10/11 上午8:38
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public void test(){
        User user = userService.findByUser();
        System.out.println(user);
    }
}
