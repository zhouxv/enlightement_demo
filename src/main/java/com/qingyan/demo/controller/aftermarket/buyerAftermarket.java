package com.qingyan.demo.controller.aftermarket;

import com.qingyan.demo.dto.AftermarketDTO;
import com.qingyan.demo.entity.Aftermarket;
import com.qingyan.demo.entity.Transaction;
import com.qingyan.demo.service.daoservice.IAftermarketService;
import com.qingyan.demo.service.daoservice.ITransactionService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家售后 controller.
 *
 * @author zhouxv 2021/7/26.
 */
@Slf4j
@Api(tags = "买家售后")
@RequestMapping({"buyerAftermarket"})
@RestController
public class buyerAftermarket {
    private final ITransactionService transactionService;
    private final IAftermarketService aftermarketService;

    public buyerAftermarket(ITransactionService transactionService, IAftermarketService aftermarketService) {
        this.transactionService = transactionService;
        this.aftermarketService = aftermarketService;
    }

    /**
     * 买家发起仲裁
     *
     * @param aftermarketDTO
     * @return
     * @apiNote 买家点击售后按钮，进入售后页面，点击提交，调用此接口
     */
    @ApiOperation(value = "买家发起仲裁", notes = "买家点击售后按钮，进入售后页面，点击提交，调用此接口")
    @PostMapping({"aftermarketStart"})
    public ResponseBody negotiate(@RequestBody AftermarketDTO aftermarketDTO) {
        log.info("买家发起仲裁");

        Transaction transaction = this.transactionService.findTransactionById(aftermarketDTO.getTransactionId().toString());
        if (transaction == null || transaction.getStatus() != 14) {
            log.info("非法操作，订单状态非法");
            return ResponseBody.fail("非法操作，订单状态非法");
        }

        Aftermarket aftermarket = aftermarketDTO.TransactionToAftermarket(transaction);
        if (!this.aftermarketService.createAftermarket(aftermarket)) {
            log.info("txId为<" + transaction.getTxid() + ">的订单，仲裁提交失败");
            return ResponseBody.fail("txId为<" + transaction.getTxid() + ">的订单，仲裁提交失败");
        }

        log.info("txId为<" + transaction.getTxid() + ">的订单，仲裁提交失败");
        Map<String, String> map = new HashMap<>();
        map.put("aftermarketId", aftermarket.getAftermarketid());
        return ResponseBody.ok("开始仲裁", map);
    }

    /**
     * 检索所有的仲裁请求
     *
     * @param aftermarket
     * @return
     * @apiNote 根据条件检索所有的仲裁请求
     */
    @PostMapping({"getAll"})
    @ApiOperation(value = "检索所有的仲裁请求", notes = "根据条件检索所有的仲裁请求")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "aftermarket", value = "仲裁单", required = true, dataTypeClass = Aftermarket.class)
    })
    public ResponseBody getAll(@RequestBody Aftermarket aftermarket) {
        List<Aftermarket> list = this.aftermarketService.findAllAftermarket(aftermarket);
        return ResponseBody.ok("检索完成", list);
    }
}
