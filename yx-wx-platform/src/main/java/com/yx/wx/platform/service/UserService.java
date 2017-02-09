package com.yx.wx.platform.service;

import com.yx.wx.platform.model.User;
import com.yx.wx.platform.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;

}
