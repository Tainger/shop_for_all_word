package com.stackoverflow.dream.service;

import com.stackoverflow.dream.common.ServerResponse;
import com.stackoverflow.dream.dao.UserMapper;
import com.stackoverflow.dream.pojo.LoginTicket;
import com.stackoverflow.dream.pojo.User;
import com.stackoverflow.dream.util.Const;
import com.stackoverflow.dream.util.MD5Util;
import com.stackoverflow.dream.util.MallUtils;
import com.stackoverflow.dream.util.RedisKeyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public ServerResponse<String> login(User user, int expiredSeconds) {
        String username = user.getUsername();
        String password = user.getPassword();
        int res = userMapper.checkUserName(username);
        if(res == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在！");
        }
        String Md5password = MD5Util.MD5EncodeUtf8(password);
        user = userMapper.findUser(username, Md5password);
        if(null == user) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        // 生成登录凭证
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setTicket(MallUtils.generateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + expiredSeconds * 1000));
        //
        String ticket = loginTicket.getTicket();
        String redisKey = RedisKeyUtils.getTicketKey(ticket);
        redisTemplate.opsForValue().set(redisKey,loginTicket);
        return ServerResponse.createBySuccess(ticket);
    }

    @Override
    public ServerResponse<User> register(User user) {
        String username = user.getUsername();
        int res = userMapper.checkUserName(username);
        if(res > 1) {
            return ServerResponse.createByErrorMessage("用户已经存在！");
        }
        String email = user.getEmail();
        res = userMapper.checkEmail(email);
        if(res > 1) {
            return ServerResponse.createByErrorMessage("邮箱已经存在！");
        }
        String password = user.getPassword();
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        user.setPassword(md5Password);
        user.setRole(Const.Role.ROLE_ADMIN);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userMapper.insertSelective(user);
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<User> checkCommon(String commonStr, Integer type) {
        if(type == 0){
            int res = userMapper.checkUserName(commonStr);
            if(res > 1) {
                return ServerResponse.createByErrorMessage("用户已经存在！");
            }
        }
        if(type == 1){
            int res = userMapper.checkEmail(commonStr);
            if(res > 1) {
                return ServerResponse.createByErrorMessage("邮箱已经存在！");
            }
        }
        return ServerResponse.createByErrorMessage("未知参数类型");
    }


    @Override
    public ServerResponse<User> getForgetQuestion(String userName) {
        User user = userMapper.findUserByUserName(userName);
        if(null == user){
            return ServerResponse.createByErrorMessage("该用户不存在");
        }
        String question = user.getQuestion();
        if(null == question){
            return ServerResponse.createByErrorMessage("该用户未设置问题");
        }
        return ServerResponse.createBySuccessMessage(question);
    }

    @Override
    public User findUserByTicket(String ticket) {
        String redisKey = RedisKeyUtils.getTicketKey(ticket);
        LoginTicket  loginTicket = (LoginTicket) redisTemplate.opsForValue().get(redisKey);
        int userId = loginTicket.getUserId();
        return userMapper.selectByPrimaryKey(userId);
    }
}
