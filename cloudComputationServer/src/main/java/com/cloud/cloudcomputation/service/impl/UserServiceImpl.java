package com.cloud.cloudcomputation.service.impl;

import com.cloud.cloudcomputation.dao.UserMapper;
import com.cloud.cloudcomputation.pojo.User;
import com.cloud.cloudcomputation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user) {
        User userTemp = userMapper.selectSelective(user);
        System.out.println(userTemp);
        if(userTemp == null) {
            return new User();
        }
        return userTemp;
    }

    @Transactional
    @Override
    public boolean register(User user) {
        System.out.println("register"+user);
        user.setUserPrimary(1);//注册的用户权限都为1
        return userMapper.insertSelective(user);
    }

    @Override
    public boolean update(User user) {
        System.out.println("update"+user);
        return userMapper.updateSelective(user);
    }
}
