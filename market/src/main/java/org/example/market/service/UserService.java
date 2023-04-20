package org.example.market.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.example.market.dao.UserDao;
import org.example.market.entity.User;
import org.example.market.utils.Email;
import org.example.market.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Service//表示是服务层
public class UserService {
    @Autowired
    UserDao dao;
    @Autowired
    Email email;
    @Autowired
    RedisTemplate redisTemplate;

    public Result list(){
        List<User> users = dao.selectList(null);
        return Result.ok(200,"查询成功",users);
    }

    //发送邮箱方法
    public Result sendCode(String userEmail){
        //判断邮箱是否注册过
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_email",userEmail);
        User user = dao.selectOne(wrapper);
        if(!ObjectUtils.isNull(user)){
            return Result.error("该邮箱已经注册!");
        }

        //生成验证码
        String code="";
        for (int i=1;i<=6;i++){
            code+=new Random().nextInt(10);
        }
        //发送验证码
        boolean result = email.sendEmail(userEmail, "验证码", "你的验证码是:" + code);

        //将验证码
        redisTemplate.opsForValue().set("code",code,30, TimeUnit.SECONDS);
        return result?Result.ok("发送成功!"):Result.error("发送失败!");

    }

    //注册
    @Transactional(rollbackFor = Exception.class)//表示发生异常时  回滚事务
    public Result register(User user){
        //1、判断验证码是否正确
        String code = redisTemplate.opsForValue().get("code") + "";//从redis中取出验证码
        if (!user.getCheckCode().equals(code)){
            return Result.error("验证码输入错误");
        }

        //2、验证码正确   进行注册
        user.setCreateTime(new Date());//添加注册时间
        int i = dao.insert(user);
        return Result.ok("注册成功!");
    }

    //登录
    public Result login(User user){
        //判断邮箱是否已经注册
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_email",user.getUserEmail());//(数据库字段,实体类字段)
        User u = dao.selectOne(wrapper);
        if(ObjectUtils.isNull(u)){
            return Result.error("该邮箱未注册!");
        }

        //执行登录
        wrapper.eq("user_password",user.getUserPassword());
        u = dao.selectOne(wrapper);
        if(ObjectUtils.isNull(u)){
            return Result.error("密码输入错误!");
        }
        return Result.ok("登录成功!");
    }

}
