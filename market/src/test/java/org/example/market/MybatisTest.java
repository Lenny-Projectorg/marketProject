package org.example.market;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.market.dao.UserDao;
import org.example.market.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;


@SpringBootTest
public class MybatisTest {
    @Autowired//往测试类中 注入UserDao
    private UserDao dao;

    //测试向数据库表中添加数据
    @Test
    public void test01(){
//        System.out.println("Hello world!");

        User u = new User();
        u.setUserName("bear");
        u.setUserPassword("123456");
        u.setUserEmail("013@qq.com");
        u.setUserBirth(new Date());
        u.setCreateTime(new Date());
        int i = dao.insert(u);
        System.out.println(i>0?"添加成功":"添加失败");
    }

    //测试查询
    @Test
    public void test02(){
        User user = dao.selectById(2);
        System.out.println("查询"+user);
    }

    //查询全部数据
    @Test
    public void test03(){
        List<User> users = dao.selectList(null);//null表示不需要条件
        for (User user : users) {
            System.out.println(user);
        }
    }

    //根据名称模糊查询
    @Test
    public void test04(){
        //创建构造器对象，构造查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("user_name","l");//表示构造模糊查询条件
        List<User> users = dao.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
