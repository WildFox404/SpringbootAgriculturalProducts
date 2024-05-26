package com.cow.spring_vue.controller;

import com.cow.spring_vue.entity.Result;
import com.cow.spring_vue.entity.User;
import com.cow.spring_vue.entity.child.UserLogin;
import com.cow.spring_vue.entity.child.UserString;
import com.cow.spring_vue.service.UserService;
import com.cow.spring_vue.utils.JwtUtil;
import com.cow.spring_vue.utils.MD5Utils;
import com.cow.spring_vue.utils.ThreadLocalUtil;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserLogin userLogin) throws IOException {
        User user = userService.login(userLogin);
        System.out.println(userLogin.getPassword());
        String MD5String = MD5Utils.encrypt(userLogin.getPassword());
        if (user==null){
            return Result.error("用户名错误");
        }
        if (user.getPassword().equals(MD5String)){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            String token = JwtUtil.getToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }


    @PostMapping("/register")
    public Result register(@RequestBody UserLogin userLogin){
        try {
            User u=userService.findByUserName(userLogin.getUsername());
            if(u==null){
                //注册
                userService.register(userLogin);
                return Result.success();
            }else{
                return Result.error("用户名已被占用");
            }
        } catch (Exception e) {
            return Result.error("注册出现错误,传入数据错误");
        }
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User user =userService.findByUserName(username);
        return Result.success(user);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Validated User user){
        try {
            Map<String,Object> map = ThreadLocalUtil.get();
            String username = (String)map.get("username");
            user.setUsername(username);
            userService.update(user);
            return Result.success();
        } catch (Exception e) {
            return Result.error("更新用户数据失败");
        }
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        try {
            userService.updateAvatar(avatarUrl);
            return Result.success();
        } catch (Exception e) {
            return Result.error(String.valueOf(e));
        }
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        String oldPwd=params.get("old_pwd");
        String newPwd=params.get("new_pwd");
        String rePwd=params.get("re_pwd");

        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要参数");
        }
        Map<String,Object> map =ThreadLocalUtil.get();
        String username =(String) map.get("username");
        User loginUser =userService.findByUserName(username);
        if(!loginUser.getPassword().equals(MD5Utils.encrypt(oldPwd))){
            return Result.error("原密码不正确");
        }

        if(!rePwd.equals(newPwd)){
            return Result.error("两次填写密码不一致");
        }

        userService.updatePwd(newPwd);
        return Result.success();
    }

}
