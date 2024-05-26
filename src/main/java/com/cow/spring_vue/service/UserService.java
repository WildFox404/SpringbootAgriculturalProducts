package com.cow.spring_vue.service;

import com.cow.spring_vue.entity.User;
import com.cow.spring_vue.entity.child.UserLogin;

import java.util.List;

public interface UserService {

    User login(UserLogin userLogin);

    User findByUserName(String username);

    void register(UserLogin userLogin);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
