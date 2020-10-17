package com.stackoverflow.dream.service;

import com.stackoverflow.dream.common.ServerResponse;
import com.stackoverflow.dream.pojo.User;

import java.util.List;

/**
 * @author rocky
 * @ClassName UserService
 * @Description
 * @Create by rocky
 * @Date 2020/10/11 上午8:38
 */

public interface UserService {


    /**
     *
     * @param user
     * @param expireMent
     * @return
     */
    ServerResponse<String> login(User user,int expireMent);

    /**
     * 注册用户
     * @param user
     * @return
     */
    ServerResponse<User> register(User user);

    /**
     * 校验邮箱或者名称是否存在
     * 0表示用户 1表示邮箱
     * @param commonStr
     * @param type
     * @return
     */
    ServerResponse<User> checkCommon(String commonStr, Integer type);


    /**
     * 获取忘记密码的问题
     * @param userName
     * @return
     */
    ServerResponse<User> getForgetQuestion(String userName);

    /**
     * 根据ticket查询User
     * @param ticket
     * @return
     */
    User findUserByTicket(String ticket);
}
