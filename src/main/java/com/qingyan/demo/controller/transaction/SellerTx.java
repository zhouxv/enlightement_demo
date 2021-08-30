package com.qingyan.demo.controller.transaction;

import com.qingyan.demo.dto.DataSetCipherDTO;
import com.qingyan.demo.dto.K2DTO;
import com.qingyan.demo.dto.OuterKeyDTO;
import com.qingyan.demo.entity.Transaction;
import com.qingyan.demo.service.daoservice.ITransactionService;
import com.qingyan.demo.service.otherservice.FileService;
import com.qingyan.demo.util.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * 卖家交易 controller.
 *
 * @author zhouxv 2021/7/25.
 */
@Slf4j
@Api(tags = "SellerTx 卖家交易")
@RequestMapping({"sellerTx"})
@RestController
public class SellerTx {

    private final ITransactionService transactionService;
    private final FileService fileService;


    public SellerTx(ITransactionService transactionService, FileService fileService) {
        this.transactionService = transactionService;
        this.fileService = fileService;
    }

    /**
     * 卖家检索所有的协商请求
     *
     * @param sellerId UUID 卖家Id
     * @return
     * @description 卖家传递自己的ID，检索所有的协商
     */
    @PostMapping({"getAllTX"})
    @ApiOperation(value = "检索所有的协商请求", notes = "卖家传递自己的ID，检索所有的协商")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "sellerId", value = "卖家Id", required = true, dataTypeClass = UUID.class)
    })
    public ResponseBody getAll(@RequestParam UUID sellerId) {
        List<Transaction> list = this.transactionService.findAllTXBySellerId(sellerId.toString(), null);
        return ResponseBody.ok("检索完成", list);
    }

    /**
     * 卖家协商
     *
     * @param txId     订单Id
     * @param bool     是否通过协商
     * @param newPrice 新的价格
     * @return
     * @apiNote 卖家通过或者驳回协商提交
     */
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

    /**
     * 卖家上传OuterKey
     *
     * @param outerKeyDTO 对称密钥，其哈希和签名
     * @return
     * @apiNote 卖家提交对称密钥，其哈希和签名
     */
    @PostMapping({"uploadOuterKey"})
    @ApiOperation(value = "卖家上传OuterKey", notes = "卖家提交对称密钥，其哈希和签名")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "outerKeyDTO", value = "outerKeyDTO", required = true, dataTypeClass = OuterKeyDTO.class)
    })
    public ResponseBody verifyOuterKey(@RequestBody OuterKeyDTO outerKeyDTO) {
        Transaction tx = outerKeyDTO.toSellerTransaction();
        // TODO: 2021/5/25 卖家签名验证
        if (this.transactionService.updateTransactionById(tx)) {
            log.info("卖家上传OuterKey完成");
            return ResponseBody.ok("卖家上传OuterKey完成");
        }
        log.info("卖家上传OuterKey失败");
        return ResponseBody.fail("卖家上传OuterKey失败");
    }

    /**
     * 卖家上传数据集密文包
     *
     * @param dataSetCipher    MultipartFile
     * @param dataSetCipherDTO DataSetCipherDTO
     * @return
     * @apiNote 卖家提交数据集密文包，以及相应的哈希和签名
     */
    @PostMapping({"uploadDataSetCipher"})
    @ApiOperation(value = "卖家上传数据集密文包", notes = "卖家提交数据集密文包，以及相应的哈希和签名")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "dataSetCipher", value = "上传的数据集加密包", required = true, dataTypeClass = MultipartFile.class),
            @ApiImplicitParam(name = "dataSetCipherDTO", value = "上传的数据集加密包证据", required = true, dataTypeClass = DataSetCipherDTO.class)
    })
    public ResponseBody uploadDataSetCipher(@RequestPart MultipartFile dataSetCipher, @RequestPart DataSetCipherDTO dataSetCipherDTO) throws Exception {
        Transaction tx = this.transactionService.findTransactionById(dataSetCipherDTO.getTxId().toString());
        if (tx == null || tx.getStatus() != 6) {
            log.info("非法操作，订单状态异常");
            return ResponseBody.fail("非法操作，订单状态异常");
        }

        if (!this.fileService.uploadDataSetCipher(dataSetCipher, dataSetCipherDTO.getDataSetCipherHash())) {
            log.info("数据集密文包存储失败，上传密文包失败");
            return ResponseBody.fail("数据集密文包存储失败，上传密文包失败");
        }

        log.info("数据集密文包存储成功");
        Transaction transaction = dataSetCipherDTO.toSellerTransaction();
        if (this.transactionService.updateTransactionById(transaction)) {
            log.info("卖家密文包完成");
            return ResponseBody.ok("卖家密文包完成");
        }

        return ResponseBody.fail("卖家密文包未完成");
    }

    /**
     * 卖家上传K2
     *
     * @param k2DTO K2DTO
     * @return
     * @apiNote 买家付款后卖家上传K2
     */
    @PostMapping({"addK2"})
    @ApiOperation(value = "卖家上传K2", notes = "买家付款后卖家上传K2")
    public ResponseBody addK2(@RequestBody K2DTO k2DTO) {
        Transaction tx = this.transactionService.findTransactionById(k2DTO.getTxId().toString());

        if (tx == null || tx.getStatus() != 11) {
            log.info("非法操作，订单状态异常");
            return ResponseBody.fail("非法操作，订单状态异常");
        }

        Transaction transaction = k2DTO.toTransaction();
        if (this.transactionService.updateTransactionById(transaction)) {
            log.info("卖家K2上传完成，订单状态更新成功");
            return ResponseBody.ok("卖家K2上传完成");
        }
        log.info("卖家K2上传失败，订单状态更新失败");
        return ResponseBody.ok("卖家K2上传失败");
    }

}
