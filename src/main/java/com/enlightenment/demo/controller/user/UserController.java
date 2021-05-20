package com.enlightenment.demo.controller.user;

import com.enlightenment.demo.entity.User;
import com.enlightenment.demo.service.daoservice.IUserService;
import com.enlightenment.demo.util.ResponseBody;
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
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


    /**
     * @param PK   用户公钥
     * @param name 用户名称
     * @param role 用户角色，1为卖家，2为买家
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "身份登记", notes = "用户传递公钥与身份和角色，操作成功代表在平台处完成了登记身份")
    @GetMapping({"register"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "name", value = "用户名称", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "PK", value = "用户公钥", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "role", value = "用户角色,1卖方2买方", required = true, dataTypeClass = String.class)
    })
    public ResponseBody register(String name, String PK, int role) throws Exception {
        User user = new User(name, PK, role);

        if (user.getUserrole() == 1) log.info("卖方开始注册");
        else log.info("买方开始注册");

        if (this.userService.createUser(user)) {
            log.info("注册成功，userId为 " + user.getUserid());
            return ResponseBody.ok("注册成功", user);
        }

        log.info("注册失败");
        return ResponseBody.fail("注册失败");
    }
}
