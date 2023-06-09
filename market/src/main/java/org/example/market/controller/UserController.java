package org.example.market.controller;

import org.example.market.entity.User;
import org.example.market.security.service.LoginService;
import org.example.market.service.UserService;
import org.example.market.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")//父路径
public class UserController {
    @Autowired
    UserService service;

    @Autowired
    LoginService loginService;

    @Autowired
    RedisTemplate redisTemplate;


    @GetMapping("/list")//localhost:端口号/user/list
    public Result list(){
        try {
            return service.list();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }

    @GetMapping("/sendCode")
    public Result sendCode(String userEmail){
        try {
            return service.sendCode(userEmail);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }


    //注册

    /**
     *
     * 请求方式：Post
     * 请求路径：/user/register
     * 所需参数：User实体类 Json格式
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        try {
            return service.register(user);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常!",e.getMessage());
        }
    }

    /**
     *用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        try {
            return loginService.login(user);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常!",e.getMessage());
        }
    }

    @GetMapping("/logout")
    public Result logout(){
        //登出===>清除redis中的用户信息
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User u = (User) auth.getPrincipal();
        redisTemplate.delete(u.getId()+"");
        return Result.ok("退出成功!");
    }

}
