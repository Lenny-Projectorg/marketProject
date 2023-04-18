package org.example.market;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//用于启动SpringBoot项目的类
@SpringBootApplication
@MapperScan("org.example.market.dao")//表示扫描dao包下的注解
public class SpringApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class);
    }
}
