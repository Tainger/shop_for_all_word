package com.stackoverflow.dream.component.interceptor;

import com.stackoverflow.dream.component.HostHolder;
import com.stackoverflow.dream.pojo.User;
import com.stackoverflow.dream.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author rocky
 * @ClassName LoginTicketInterceptor
 * @Description
 * @Create by rocky
 * @Date 2020/10/17 下午12:22
 */

@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

    private static  final Logger logger = LoggerFactory.getLogger(LoginTicketInterceptor.class);

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从ticket中获取User
        String ticket = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if(name.equals("ticket")){
                ticket = cookie.getValue();
                logger.error(ticket);
            }
        }
        User user = userService.findUserByTicket(ticket);
        if(null != user){
            hostHolder.setUsers(user);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        User user = hostHolder.getUser();
        if(null != user && modelAndView != null) {
            modelAndView.addObject(user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        hostHolder.clear();
    }

}
