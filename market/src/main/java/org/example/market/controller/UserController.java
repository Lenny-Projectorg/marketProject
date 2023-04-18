package org.example.market.controller;

import org.example.market.service.UserService;
import org.example.market.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")//父路径
public class UserController {
    @Autowired
    UserService service;

    @GetMapping("list")//localhost:端口号/user/list
    public Result list(){
        try {
            return service.list();
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }
}
