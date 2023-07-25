package com.jgq.controller;

import com.jgq.pojo.User;
import com.jgq.service.UrlService;
import com.jgq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //声明urlService属性
    @Autowired
    private UrlService urlService;

    /**
     * 用户登录功能
     *
     * @param uname
     * @param pwd
     * @param session
     * @return user!=null
     */
    @RequestMapping("userLogin")
    @ResponseBody
    public Boolean userLogin(String uname, String pwd, HttpSession session) {
        User user = userService.selUserInfoService(uname, pwd);
        //设置session的有效期
        session.setMaxInactiveInterval(60 * 60);
        //判断用户是否登录成功
        if (user != null) {
            //获取用户的url地址信息
            List<String> urls = urlService.selUrlInfoByRidService(user.getRid());
            //将url数据存储到session中
            session.setAttribute("urls", urls);
            //将用户信息存储到session中
            session.setAttribute("user", user);
        }
        return user != null;
    }
}
