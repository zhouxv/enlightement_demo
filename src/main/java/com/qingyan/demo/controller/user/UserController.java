package com.qingyan.demo.controller.user;

import com.qingyan.demo.dto.UserDTO;
import com.qingyan.demo.entity.User;
import com.qingyan.demo.service.daoservice.IUserService;
import com.qingyan.demo.util.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息模块 controller.
 *
 * @author zhouxv 2021/7/20.
 */
@Slf4j
@Api(tags = "User 用户信息模块")
@RequestMapping({"user"})
@RestController
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 用户注册
     *
     * @param userDTO 用户信息DTO
     * @return
     * @apiNote 用户传递公钥，操作成功代表在平台处完成了登记身份
     */
    @ApiOperation(value = "用户注册", notes = "用户传递公钥，操作成功代表在平台处完成了登记身份")
    @PostMapping({"register"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userDTO", value = "用户信息", required = true, dataTypeClass = UserDTO.class)
    })
    public ResponseBody register(@RequestBody UserDTO userDTO) {
        User user = userDTO.toUser();
        if (this.userService.createUser(user)) {
            log.info("注册成功，userId为 " + user.getUserid());
            return ResponseBody.ok("注册成功", user);
        }

        log.info("注册失败");
        return ResponseBody.fail("注册失败");
    }
}
