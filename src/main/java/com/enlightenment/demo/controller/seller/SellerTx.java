package com.enlightenment.demo.controller.seller;

import com.enlightenment.demo.dto.DataSetCipherDTO;
import com.enlightenment.demo.entity.Transaction;
import com.enlightenment.demo.service.daoservice.ITransactionService;
import com.enlightenment.demo.util.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@Api(tags = "SellerTx 卖家交易")
@RequestMapping({"sellerTx"})
public class SellerTx {

    private final ITransactionService transactionService;

    public SellerTx(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping({"getAllTX"})
    @ApiOperation(value = "检索所有的协商请求", notes = "卖家传递自己的公钥，检索所有的协商")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "sellerId", value = "卖家Id", required = true, dataTypeClass = UUID.class)
    })
    public ResponseBody getAll(@RequestParam UUID sellerId) {
        List<Transaction> list = this.transactionService.findAllTXBySellerId(sellerId.toString(), null);
        return ResponseBody.ok("检索完成", list);
    }

    @GetMapping({"negotiate"})
    @ApiOperation(value = "协商", notes = "卖家通过或者驳回协商提交")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "txId", value = "交易Id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "bool", value = "协商结果，true为通过，false为驳回", required = true, dataTypeClass = Boolean.class),
            @ApiImplicitParam(name = "newPrice", value = "新的价格", dataTypeClass = Double.class)
    })
    public ResponseBody negotiate(@RequestParam UUID txId, boolean bool, Double newPrice) {
        // TODO: 2021/5/20 txid验证，用户验证，订单状态检验

        if (bool) {
            Transaction tx = new Transaction(txId.toString(), 2);

            if (this.transactionService.updateTransactionById(tx)) {
                log.info("卖家协商通过成功");
                return ResponseBody.ok("卖家协商通过操作 succeed");
            }

            return ResponseBody.fail("卖家协商通过操作 failed");
        } else {
            Transaction tx = new Transaction(txId.toString(), newPrice, 3);

            if (this.transactionService.updateTransactionById(tx)) {
                log.info("卖家协商驳回成功");
                return ResponseBody.ok("卖家协商驳回操作 succeed");
            }

            return ResponseBody.fail("卖家协商驳回操作 failed");
        }
    }

    @GetMapping({"verifyOuterKey"})
    @ApiOperation(value = "确认outerKey", notes = "卖家提交对称密钥的哈希和签名")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "txId", value = "交易Id", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "outerKeyHash", value = "outerKey的哈希", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "outerKeySig", value = "卖家对outerKey的签名", required = true, dataTypeClass = String.class)
    })
    public ResponseBody verifyOuterKey(String txId, String outerKeyHash, String outerKeySig) {

        return ResponseBody.ok("卖家outerKEy确认完成", null);
    }

    @PostMapping({"uploadDataSetCipher"})
    @ApiOperation(value = "上传数据集密文包", notes = "卖家提交数据集密文包，以及相应的哈希和签名")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "dataSetCipher", value = "上传的数据集加密包", required = true, dataTypeClass = MultipartFile.class),
            @ApiImplicitParam(name = "dataSetCipherDTO", value = "上传的数据集加密包证据", required = true, dataTypeClass = DataSetCipherDTO.class)
    })
    public ResponseBody uploadDataSetCipher(@RequestPart MultipartFile dataSetCipher, @RequestPart DataSetCipherDTO dataSetCipherDTO) {

        return ResponseBody.ok("数据集密文包发送完成", null);
    }

    @PostMapping({"addK2"})
    @ApiOperation(value = "上传K2", notes = "买家付款后卖家上传K2")
    public ResponseBody addK2(@RequestBody String K2) {
        return ResponseBody.ok("K2传递成功", null);
    }

}
