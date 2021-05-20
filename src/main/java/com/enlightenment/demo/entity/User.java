package com.enlightenment.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Entity
 *
 * @author zhouxv@gmail.com
 * @date 2021-05-19 11:22:11
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

    /**
     * 用户角色，1卖家，2买家
     */
    @TableField("userRole")
    private Integer userrole;


    public User() {
    }

    public User(String username, String userpublickey, Integer userrole) {
        this.username = username;
        this.userpublickey = userpublickey;
        this.userrole = userrole;
    }
}
