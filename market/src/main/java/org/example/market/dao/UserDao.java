package org.example.market.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.market.entity.User;
import org.springframework.stereotype.Repository;

@Repository//表示是dao层
public interface UserDao extends BaseMapper<User> {//使用API实现增删查改（仅限单表查询）
}
