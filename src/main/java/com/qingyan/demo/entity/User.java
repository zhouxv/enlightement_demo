package com.qingyan.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Entity
 *
 * @author helit747@gmail.com
 * @date 2021-06-27 19:19:48
 */
@Data
@TableName("user")
public class User {

    /**
     * 用户Id
     */
    @TableId(value = "userId", type = IdType.ASSIGN_UUID)
    private String userid;

    /**
     * 用户名称
     */
    @TableField("userName")
    private String username;

    /**
     * 用户公钥
     */
    @TableField("userPublicKey")
    private String userpublickey;

    public User() {
    }
}
