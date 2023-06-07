package com.cloud.cloudcomputation.service;

import com.cloud.cloudcomputation.pojo.User;

public interface UserService {
    User login(User user);

    boolean register(User user);

    boolean update(User user);
}
