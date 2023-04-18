package org.example.market.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data//生成set/get
@AllArgsConstructor//全参构造
@NoArgsConstructor //无参构造
@ToString
public class User {
    @TableId(type = IdType.AUTO)//表示id是主键 且自增
    private Integer id;

    private String userName;

    private String userPassword;

    private String userEmail;

    @DateTimeFormat(pattern = "yyyy-MM-dd")//处理前端接收的
    @JsonFormat(pattern = "yyyy-MM-dd")//处理后端接收的
    private Date userBirth;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//处理前端接收的
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//处理后端接收的
    private Date createTime;

}
