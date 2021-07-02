package com.enlightenment.demo.controller.aftermarket;

import com.enlightenment.demo.entity.Aftermarket;
import com.enlightenment.demo.entity.Transaction;
import com.enlightenment.demo.service.daoservice.IAftermarketService;
import com.enlightenment.demo.service.daoservice.ITransactionService;
import com.enlightenment.demo.util.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@Api(tags = "卖家售后")
@RequestMapping({"sellerAftermarket"})
public class sellerAftermarket {
    private final ITransactionService transactionService;
    private final IAftermarketService aftermarketService;

    public sellerAftermarket(ITransactionService transactionService, IAftermarketService aftermarketService) {
        this.transactionService = transactionService;
        this.aftermarketService = aftermarketService;
    }

    @PostMapping({"getAll"})
    @ApiOperation(value = "根据条件检索所有的仲裁请求", notes = "根据条件检索所有的仲裁请求")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "buyerId", value = "卖家Id", required = true, dataTypeClass = UUID.class)
    })
    public ResponseBody getAll(@RequestBody Aftermarket aftermarket) {
        List<Aftermarket> list = this.aftermarketService.findAllAftermarket(aftermarket);
        return ResponseBody.ok("检索完成", list);
    }

    @GetMapping({"confirm"})
    @ApiOperation(value = "卖家仲裁确认", notes = "卖家对仲裁的订单进行确认")
    public ResponseBody confirm(@RequestParam UUID aftermarketId, boolean bool) {
        Aftermarket aftermarket = this.aftermarketService.findAftermarketById(aftermarketId.toString());


        if (aftermarket == null || aftermarket.getReturnstatus() != 1 || aftermarket.getReturnstatus() != 3) {
            log.info("非法操作，售后状态非法");
            return ResponseBody.fail("非法操作，售后状态非法");
        }

        if (bool) {
            log.info("卖家售后验证通过");
            aftermarket.setReturnstatus(2);
            if (this.aftermarketService.updateAftermarketById(aftermarket)) {
                log.info("卖家售后仲裁通过，更新成功");
            } else {
                log.info("卖家售后仲裁通过，更新失败");
                return ResponseBody.fail("卖家售后仲裁通过，更新失败");
            }

            Transaction transaction = new Transaction(aftermarket.getTransactionid(), 13);
            if (this.transactionService.updateTransactionById(transaction)) {
                log.info("卖家售后仲裁通过，订单状态更新成功");
                return ResponseBody.ok("卖家售后仲裁通过，订单状态更新成功");
            }
            log.info("卖家售后仲裁通过，但订单状态更新失败");
            return ResponseBody.fail("卖家售后仲裁通过，但订单状态更新失败");
        } else {
            log.info("卖家售后验证拒绝");
            aftermarket.setReturnstatus(3);
            if (this.aftermarketService.updateAftermarketById(aftermarket)) {
                log.info("卖家售后仲裁拒绝，更新成功");
                return ResponseBody.ok("卖家售后仲裁拒绝，更新成功");
            } else {
                log.info("卖家售后仲裁拒绝，更新失败");
                return ResponseBody.fail("卖家售后仲裁拒绝，更新失败");
            }
        }
    }

}
