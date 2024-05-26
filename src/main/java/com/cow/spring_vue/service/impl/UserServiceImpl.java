package com.cow.spring_vue.service.impl;

import com.cow.spring_vue.entity.User;
import com.cow.spring_vue.entity.child.UserLogin;
import com.cow.spring_vue.mapper.UserMapper;
import com.cow.spring_vue.service.UserService;
import com.cow.spring_vue.utils.MD5Utils;
import com.cow.spring_vue.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserLogin userLogin) {
        User user = userMapper.findByUserName(userLogin.getUsername());
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public User findByUserName(String username) {
        User user =userMapper.findByUserName(username);
        return user;
    }


    public void register(UserLogin userLogin){
        String md5string = MD5Utils.encrypt(userLogin.getPassword());
        userMapper.register(userLogin.getUsername(),md5string);
    }

    @Override
    public void update(User user) {

        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id =(Integer)map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map =ThreadLocalUtil.get();
        Integer id = (Integer)map.get("id");
        userMapper.updatePwd(MD5Utils.encrypt(newPwd),id);
    }
}
