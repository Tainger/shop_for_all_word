package com.stackoverflow.dream.service;

import com.stackoverflow.dream.dao.UserMapper;
import com.stackoverflow.dream.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rocky
 * @ClassName UserServiceImpl
 * @Description
 * @Create by rocky
 * @Date 2020/10/11 上午8:43
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUser() {
        System.out.println(userMapper);
        return  userMapper.selectByPrimaryKey(13);
    }
}
