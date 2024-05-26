package com.cow.spring_vue.entity.child;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

public class UserString {

    private Integer id;

    @Size(min = 5, max = 16)
    private String username;
    @JsonIgnore
    @Size(min = 5, max = 16)
    private String password;
    private String nickname;
    @Email
    private String email;
    private String userPic;
    private String create_Time;
    private String update_Time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getCreate_Time() {
        return create_Time;
    }

    public void setCreate_Time(String create_Time) {
        this.create_Time = create_Time;
    }

    public String getUpdate_Time() {
        return update_Time;
    }

    public void setUpdate_Time(String update_Time) {
        this.update_Time = update_Time;
    }
}
