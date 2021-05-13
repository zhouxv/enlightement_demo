package com.enlightenment.demo.controller.user;

import com.enlightenment.demo.dto.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "Transaction 交易模块")
@RequestMapping({"tx"})
public class Transaction {

    @PostMapping({"getOuterKey"})
    @ApiOperation(value = "获取共享密钥OuterKey", notes = "根据交易Id获取共享密钥")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "txId", value = "交易Id", required = true)
    })
    public ResponseBody getOuterKey(String txId) {
        Map<String, String> map = new HashMap<>();
        map.put("OuterKey", "xxxxxxxx");
        return ResponseBody.ok("数据权声明成功", map);
    }

}
