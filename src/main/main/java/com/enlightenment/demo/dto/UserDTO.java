package com.enlightenment.demo.dto;

import com.enlightenment.demo.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/*
@Note：

@User：NineSun
@Time:2021/5/20   14:42
*/
@Data
@ApiModel(value = "UserDTO", description = "用户DTO")
public class UserDTO {
    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户公钥")
    private String PK;


    public User toUser() {
        User user = new User();
        user.setUsername(userName);
        user.setUserpublickey(PK);
        return user;
    }
}
