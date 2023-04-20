package org.example.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//视图控制器（返回、渲染页面）
public class ViewController {
    @RequestMapping("/test")//路径：localhost:8081/test
    public String test(){
        return "test";//  "/templates/"+test+".html"
    }

    //注册界面
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    //登录界面
    @RequestMapping("/")
    public String login(){
        return "login";
    }

    //主界面
    @RequestMapping("/index")
    public String index(){
        return "shop";
    }
}
