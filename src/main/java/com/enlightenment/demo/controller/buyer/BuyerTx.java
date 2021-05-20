package com.enlightenment.demo.controller.buyer;

import com.enlightenment.demo.dto.DataSetCipherDTO;
import com.enlightenment.demo.dto.NegotiateDTO;
import com.enlightenment.demo.entity.Transaction;
import com.enlightenment.demo.service.daoservice.ITransactionService;
import com.enlightenment.demo.util.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Slf4j
@RestController
@Api(tags = "BuyerTx 买家交易")
@RequestMapping({"buyerTx"})
public class BuyerTx {
    private final ITransactionService transactionService;

    public BuyerTx(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @ApiOperation(value = "买家开始协商", notes = "买家点击交易按钮，进入协商页面，点击提交，调用此接口")
    @PostMapping({"negotiate"})
    public ResponseBody negotiate(@RequestBody NegotiateDTO negotiateDTO) throws ExecutionException, InterruptedException {
        log.info("开始协商");
        // TODO: 2021/5/20  用户验证和数据集验证，不做验证也可，数据库有外键约束

        Transaction tx = negotiateDTO.toTransaction();
        if (!this.transactionService.createTransaction(tx)) {
            log.info("订单生成失败");
            return ResponseBody.fail("订单生成失败,协商中断");
        }

        log.info("订单生成成功");
        Map<String, String> map = new HashMap<>();
        map.put("txId", tx.getTxid());
        return ResponseBody.ok("开始协商，订单生成成功", map);
    }

    @GetMapping({"verifyOuterKey"})
    @ApiOperation(value = "确认outerKey", notes = "买家提交对称密钥的哈希和签名")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "txId", value = "交易Id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "outerKeyHash", value = "outerKey的哈希", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "outerKeySig", value = "买家对outerKey的签名", required = true, dataTypeClass = String.class)
    })
    public ResponseBody verifyOuterKey(String txId, String outerKeyHash, String outerKeySig) {

        return ResponseBody.ok("买家outerKEy确认完成", null);
    }

    @PostMapping({"downloadDataSetCipher"})
    @ApiOperation(value = "下载数据集密文包", notes = "买家下载数据集的密文包")
    public ResponseBody downloadDataSetCipher(HttpServletResponse response, @RequestParam String txId) {

        return ResponseBody.ok("下载完成", null);
    }

    @PostMapping({"verifyDataSetCipher"})
    @ApiOperation(value = "买家确认数据集密文", notes = "买家传递收到的密文包的哈希和签名")
    public ResponseBody verifyDataSetCipher(@RequestBody DataSetCipherDTO dataSetCipherDTO) {
        return ResponseBody.ok("买家确认完毕", null);
    }

    @PostMapping({"downloadSample"})
    @ApiOperation(value = "下载sample数据集", notes = "买家下载sample数据集")
    public ResponseBody downloadSampleDataSetup(HttpServletResponse response, @RequestParam String txId) {

        return ResponseBody.ok("下载完成", null);
    }

    @GetMapping({"decision"})
    @ApiOperation(value = "买家决定交易是否继续进行", notes = "买家查看样本数据后，决定是否继续进行交易")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "txId", value = "交易Id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "bool", value = "决定结果，true为继续交易，进入付款流程，false为终止交易", required = true, dataTypeClass = Boolean.class)
    })
    public ResponseBody decision(String txId, boolean bool) {
        if (bool) return ResponseBody.ok("继续交易", null);
        return ResponseBody.ok("终止交易", null);
    }

    @GetMapping({"getK2"})
    @ApiOperation(value = "下载K2", notes = "买家在卖家上传K2后，下载K2")
    public ResponseBody uploadK2(String txId) {
        return ResponseBody.ok("K2传递成功", null);
    }
}
