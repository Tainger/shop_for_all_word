package com.stackoverflow.dream.component;

import com.stackoverflow.dream.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @author rocky
 * @ClassName HostHolder
 * @Description
 * @Create by rocky
 * @Date 2020/10/17 下午1:26
 */

@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();

    /**
     * 从threadLocal中获取User
     * @return
     */
    public User getUser() {
        return users.get();
    }


    /**
     * 设置user
     * @param user
     */
    public void setUsers(User user) {
        users.set(user);
    }

    /**
     * 线程移除
     */
    public void clear(){
        users.remove();
    }
}
