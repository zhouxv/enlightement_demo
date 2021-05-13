package com.enlightenment.demo.controller.user;

import com.enlightenment.demo.dto.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@Api(tags = "User 用户信息模块")
@RequestMapping({"user"})
public class User {
    @ApiOperation(value = "身份登记", notes = "用户传递公钥与身份和角色，操作成功代表在平台处完成了登记身份")
    @GetMapping({"register"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "PK", value = "用户公钥", required = true),
            @ApiImplicitParam(name = "name", value = "用户名称", required = true),
            @ApiImplicitParam(name = "role", value = "用户角色,1卖方2买方", required = true),
    })
    public ResponseBody register(String PK, String name, int role) {
        return ResponseBody.ok(name + " 身份登记完成", null);
    }
}
