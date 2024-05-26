package com.cow.spring_vue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cow.spring_vue.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUserName(String username);
    @Insert("insert into users(username,password,create_time,update_time)" +
            " values(#{username},#{password},now(),now())")
    void register(String username, String password);

    @Update("update users set nickname=#{nickname},email=#{email},update_Time=now() where username=#{username}")
    void update(User user);

    @Update("update users set user_pic=#{avatarUrl},update_Time=now() where id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);

    @Update("update users set password=#{encrypt},update_Time=now() where id=#{id}")
    void updatePwd(String encrypt, Integer id);
}
