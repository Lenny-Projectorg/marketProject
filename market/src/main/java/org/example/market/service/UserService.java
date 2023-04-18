package org.example.market.service;

import org.example.market.dao.UserDao;
import org.example.market.entity.User;
import org.example.market.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//表示是服务层
public class UserService {
    @Autowired
    UserDao dao;

    public Result list(){
        List<User> users = dao.selectList(null);
        return Result.ok(200,"查询成功",users);
    }
}
