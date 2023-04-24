package org.example.market.controller;

import org.example.market.jwt.JwtToken;
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
    @JwtToken(type = false)
    public String login(){
        return "login";
    }

    //主界面
    @RequestMapping("/index")
    public String index(){
        return "shop";
    }

    //详情界面
    @RequestMapping("/detail")
    public String detail(){
        return "detail";
    }

    //支付界面
    @RequestMapping("/pay")
    public String pay(){
        return "payTest";
    }

    //购物车界面
    @RequestMapping("/cart")
    public String cart(){
        return "cart";
    }
}
