package com.jgq.service;

import com.jgq.pojo.User;

public interface UserService {
    /**
     * 用户登录
     */
    User selUserInfoService(String uname, String pwd);
}
