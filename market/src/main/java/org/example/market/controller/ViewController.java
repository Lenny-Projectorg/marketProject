package org.example.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//视图控制器（返回、渲染页面）
public class ViewController {
    @RequestMapping("/test")//路径：localhost:8081/test
    public String test(){
        return "test";//  "/templates/"+test+".html"
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }
}
