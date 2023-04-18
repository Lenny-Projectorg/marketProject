package org.example.market;

import org.example.market.dao.UserDao;
import org.example.market.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


@SpringBootTest
public class MybatisTest {
    @Autowired//往测试类中 注入UserDao
    private UserDao dao;

    //测试向数据库表中添加数据
    @Test
    public void test01(){
//        System.out.println("Hello world!");

        User u = new User();
        u.setUserName("zs");
        u.setUserPassword("123456");
        u.setUserEmail("123@qq.com");
        u.setUserBirth(new Date());
        u.setCreateTime(new Date());
        int i = dao.insert(u);
        System.out.println(i>0?"添加成功":"添加失败");
    }
}
