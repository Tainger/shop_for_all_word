package com.stackoverflow.dream.controller.portal;

import com.stackoverflow.dream.common.ServerResponse;
import com.stackoverflow.dream.component.HostHolder;
import com.stackoverflow.dream.pojo.User;
import com.stackoverflow.dream.service.UserService;
import com.stackoverflow.dream.util.Const;
import com.stackoverflow.dream.util.MmMallConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author rocky
 * @ClassName UserController
 * @Description
 * @Create by rocky
 * @Date 2020/10/11 上午8:38
 */
@RestController
@RequestMapping("api/v1.0/user")
public class UserController implements MmMallConstants {

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @PostMapping("login")
    public ServerResponse<String> login(@RequestBody User user,
                                      HttpSession session, HttpServletResponse response){
        /**
         * 像登陆这样都是关于一个表的的操作就可以放在同一个service的函数返回最终结果。
         */

        ServerResponse<String> res = userService.login(user, DEFAULT_EXPIRED_SECONDS);
        String ticket = res.getData();

        Cookie cookie = new Cookie("ticket", ticket);
        cookie.setPath("/ali-education");
        cookie.setMaxAge(3600 * 24 * 100);
        response.addCookie(cookie);
        return res;
    }

    @PostMapping("register")
    public ServerResponse<User> register(@RequestBody User user){
        return userService.register(user);
    }


    @GetMapping("checkCommon")
    public ServerResponse<User> checkCommon(String commonStr, Integer type){
        return userService.checkCommon(commonStr, type);
    }

    @GetMapping("profile")
    public ServerResponse<User> getUserInfo(HttpRequest request, HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(null == user){
            return ServerResponse.createByErrorMessage("用户未登陆！");
        }
        return ServerResponse.createBySuccess(user);
    }

    @GetMapping("forget")
    public ServerResponse<User> getForgetQuestion(String userName){
        return userService.getForgetQuestion(userName);
    }



}
