package org.example.market.controller;

import org.example.market.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

//给浏览器响应数据
@RestController//返回Json格式数据     json格式{"name":"zs","age":18}
public class DataController {
    @GetMapping("/testData")
    public Result test(){
        try {
            System.out.println(1/0);
            return Result.ok(200,"测试成功", LocalDateTime.now());
        }catch (Exception e){
            return Result.error(500,"发生了异常",e.getMessage());
        }
    }
}
