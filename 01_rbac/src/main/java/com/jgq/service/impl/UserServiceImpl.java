package com.jgq.service.impl;

import com.jgq.mapper.UserMapper;
import com.jgq.pojo.User;
import com.jgq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     */
    @Override
    public User selUserInfoService(String uname, String pwd) {
        return userMapper.selUserInfoMapper(uname, pwd);
    }
}
