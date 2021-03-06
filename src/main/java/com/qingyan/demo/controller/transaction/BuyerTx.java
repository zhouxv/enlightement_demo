package com.qingyan.demo.controller.transaction;

import com.qingyan.demo.dto.DataSetCipherDTO;
import com.qingyan.demo.dto.NegotiateDTO;
import com.qingyan.demo.dto.OuterKeyDTO;
import com.qingyan.demo.entity.DataSet;
import com.qingyan.demo.entity.Goods;
import com.qingyan.demo.entity.Transaction;
import com.qingyan.demo.service.daoservice.IDataSetService;
import com.qingyan.demo.service.daoservice.IGoodsService;
import com.qingyan.demo.service.daoservice.ITransactionService;
import com.qingyan.demo.service.otherservice.FileService;
import com.qingyan.demo.util.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 买家交易 controller.
 *
 * @author zhouxv 2021/7/24.
 */
@Slf4j
@Api(tags = "BuyerTx 买家交易")
@RequestMapping({"buyerTx"})
@RestController
public class BuyerTx {
    private final ITransactionService transactionService;
    private final IDataSetService dataSetService;
    private final FileService fileService;
    private final IGoodsService goodsService;

    public BuyerTx(ITransactionService transactionService, FileService fileService, IDataSetService dataSetService, IGoodsService goodsService) {
        this.transactionService = transactionService;
        this.fileService = fileService;
        this.dataSetService = dataSetService;
        this.goodsService = goodsService;
    }

    /**
     * 买家开始协商
     *
     * @param negotiateDTO 协商信息DTO
     * @return 无
     * @apiNote 买家点击交易按钮，进入协商页面，点击提交，调用此接口
     */
    @ApiOperation(value = "买家开始协商", notes = "买家点击交易按钮，进入协商页面，点击提交，调用此接口")
    @PostMapping({"negotiate"})
    public ResponseBody negotiate(@RequestBody NegotiateDTO negotiateDTO) {
        log.info("开始协商");
        // TODO: 2021/5/20  用户验证和数据集验证，不做验证也可，数据库有外键约束
        Goods goods = this.goodsService.findGoodsById(negotiateDTO.getGoodsId().toString());
        if (goods == null) {
            log.info("invalid goodsId");
            return ResponseBody.fail("非法商品Id");
        }
        Transaction tx = negotiateDTO.toTransaction();

        tx.setDatasetid(goods.getDatasetid());
        tx.setSellerid(goods.getSellerid());
        tx.setContracttype(goods.getContracttype());

        if (!this.transactionService.createTransaction(tx)) {
            log.info("订单生成失败");
            return ResponseBody.fail("订单生成失败,协商中断");
        }

        log.info("订单生成成功");
        Map<String, String> map = new HashMap<>();
        map.put("txId", tx.getTxid());
        return ResponseBody.ok("开始协商，订单生成成功", map);
    }


    /**
     * 检索所有的协商请求
     *
     * @param buyerId UUID
     * @return 无
     * @apiNote 买家传递自己的公钥，检索所有的协商
     */
    @PostMapping({"getAllTX"})
    @ApiOperation(value = "检索所有的协商请求", notes = "买家传递自己的公钥，检索所有的协商")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "buyerId", value = "卖家Id", required = true, dataTypeClass = UUID.class)
    })
    public ResponseBody getAll(@RequestParam UUID buyerId) {
        List<Transaction> list = this.transactionService.findAllTXByBuyerId(buyerId.toString(), null);
        return ResponseBody.ok("检索完成", list);
    }

    /**
     * 买家拿到outerKey
     *
     * @param txId UUID
     * @return 无
     * @apiNote 买家拿到卖家上传的outerKey
     */
    @GetMapping({"getOuterKey"})
    @ApiOperation(value = "买家拿到outerKey", notes = "买家拿到卖家上传的outerKey")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "txId", value = "交易Id", required = true, dataTypeClass = UUID.class)
    })
    public ResponseBody getOuterKey(@RequestParam UUID txId) {
        Transaction tx = this.transactionService.findTransactionById(txId.toString());

        if (tx == null || tx.getStatus() != 4) {
            log.info("非法操作，订单状态异常");
            return ResponseBody.fail("非法操作，订单状态异常");
        }

        Transaction transaction = new Transaction(txId.toString(), 5);
        if (this.transactionService.updateTransactionById(transaction)) {
            log.info("订单状态更新成功");
        } else {
            log.info("订单状态更新失败");
            return ResponseBody.fail("订单状态更新失败");
        }

        log.info("outerKey检索成功");
        Map<String, String> map = new HashMap<>();
        map.put("outerKey", tx.getOuterkey());
        return ResponseBody.ok("outerKey检索成功", map);
    }

    /**
     * 确认outerKey
     *
     * @param outerKeyDTO OuterKeyDTO
     * @return 无
     * @apiNote 买家提交对称密钥的哈希和签名
     */
    @PostMapping({"verifyOuterKey"})
    @ApiOperation(value = "确认outerKey", notes = "买家提交对称密钥的哈希和签名")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "outerKeyDTO", value = "outerKeyDTO", required = true, dataTypeClass = OuterKeyDTO.class)
    })
    public ResponseBody verifyOuterKey(@RequestBody OuterKeyDTO outerKeyDTO) {
        Transaction tx = this.transactionService.findTransactionById(outerKeyDTO.getTxId().toString());
        if (tx == null || tx.getStatus() != 5) {
            log.info("非法操作，订单状态异常");
            return ResponseBody.fail("非法操作，订单状态异常");
        }

        Transaction transaction = outerKeyDTO.toBuyerTransaction();

        // TODO: 2021/5/25 买家签名验证
        if (this.transactionService.updateTransactionById(transaction)) {
            log.info("买家验证OuterKey完成");
            return ResponseBody.ok("买家验证OuterKey完成");
        }

        log.info("买家验证OuterKey失败");
        return ResponseBody.fail("买家验证OuterKey失败");
    }

    /**
     * 下载数据集密文包
     *
     * @param txId UUID
     * @apiNote 买家下载数据集的密文包
     */
    @GetMapping({"downloadDataSetCipher"})
    @ApiOperation(value = "下载数据集密文包", notes = "买家下载数据集的密文包")
    public void downloadDataSetCipher(HttpServletResponse response, @RequestParam UUID txId) {
        Transaction tx = this.transactionService.findTransactionById(txId.toString());

        if (!((tx == null) || (tx.getStatus() < 7))) {
            this.download(tx.getSellerdatasetcipherdigest(), 1, response);
        } else log.info("非法操作，订单状态错误");

    }

    /**
     * 买家确认数据集密文
     *
     * @param dataSetCipherDTO DataSetCipherDTO
     * @return 无
     * @apiNote 买家传递收到的密文包的哈希和签名
     */
    @PostMapping({"verifyDataSetCipher"})
    @ApiOperation(value = "买家确认数据集密文", notes = "买家传递收到的密文包的哈希和签名")
    public ResponseBody verifyDataSetCipher(@RequestBody DataSetCipherDTO dataSetCipherDTO) {
        Transaction tx = this.transactionService.findTransactionById(dataSetCipherDTO.getTxId().toString());
        if (tx == null || tx.getStatus() != 7) {
            log.info("非法操作，订单状态异常");
            return ResponseBody.fail("非法操作，订单状态异常");
        }

        Transaction transaction = dataSetCipherDTO.toBuyerTransaction();
        if (this.transactionService.updateTransactionById(transaction)) {
            log.info("买家密文包完成");
            return ResponseBody.ok("买家密文包完成");
        }

        return ResponseBody.fail("买家密文包失败");
    }

    /**
     * 下载sample数据集
     *
     * @param txId UUID
     * @apiNote 买家下载sample数据集
     */
    @GetMapping({"downloadSample"})
    @ApiOperation(value = "下载sample数据集", notes = "买家下载sample数据集")
    public void downloadSampleDataSetup(HttpServletResponse response, @RequestParam UUID txId) {
        Transaction tx = this.transactionService.findTransactionById(txId.toString());
        if (!((tx == null) || (tx.getStatus() < 7))) {
            DataSet dataSet = this.dataSetService.findDataSetById(tx.getDatasetid());
            this.download(dataSet.getSamplehash(), 0, response);
        } else log.info("非法操作，订单状态错误");
    }

    /**
     * 买家决定交易是否继续进行
     *
     * @param txId UUID
     * @param bool boolean
     * @return 无
     * @apiNote 买家查看样本数据后，决定是否继续进行交易
     */
    @GetMapping({"decision"})
    @ApiOperation(value = "买家决定交易是否继续进行", notes = "买家查看样本数据后，决定是否继续进行交易")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "txId", value = "交易Id", required = true, dataTypeClass = UUID.class),
            @ApiImplicitParam(name = "bool", value = "决定结果，true为继续交易，进入付款流程，false为终止交易", required = true, dataTypeClass = Boolean.class)
    })
    public ResponseBody decision(@RequestParam UUID txId, boolean bool) {
        Transaction tx = this.transactionService.findTransactionById(txId.toString());
        if (tx == null || tx.getStatus() != 8) {
            log.info("非法操作，订单状态错误");
            return ResponseBody.fail("非法操作，订单状态错误");
        }

        if (bool) {
            log.info("买家决定继续交易");
            Transaction transaction = new Transaction(txId.toString(), 9);
            if (this.transactionService.updateTransactionById(transaction)) {
                log.info("订单状态更新成功");
                return ResponseBody.ok("继续交易");
            }
            log.info("订单状态更新失败");
            return ResponseBody.fail("继续交易的订单状态更新失败");
        } else {
            log.info("买家决定终止交易");
            Transaction transaction = new Transaction(txId.toString(), 10);
            if (this.transactionService.updateTransactionById(transaction)) {
                log.info("订单状态更新成功");
                return ResponseBody.ok("终止交易");
            }
            log.info("订单状态更新失败");
            return ResponseBody.fail("终止交易的订单状态更新失败");
        }
    }

    /**
     * 买家支付完成调用此接口
     *
     * @param txId UUID
     * @return 无
     * @apiNote 买家支付完成调用此接口
     */
    @GetMapping({"pay"})
    @ApiOperation(value = "买家支付完成")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "txId", value = "交易Id", required = true, dataTypeClass = UUID.class)
    })
    public ResponseBody pay(@RequestParam UUID txId) {
        Transaction tx = this.transactionService.findTransactionById(txId.toString());
        if (tx == null || tx.getStatus() != 9) {
            log.info("非法操作，订单状态错误");
            return ResponseBody.fail("非法操作，订单状态错误");
        }

        log.info("付款完成");
        Transaction transaction = new Transaction(txId.toString(), 11);
        if (this.transactionService.updateTransactionById(transaction)) {
            log.info("订单状态更新成功");
            return ResponseBody.ok("付款完成");
        }
        log.info("订单状态更新失败");
        return ResponseBody.fail("付款完成的订单状态更新失败");
    }

    /**
     * 下载K2
     *
     * @param txId UUID
     * @return 无
     * @apiNote 买家在卖家上传K2后，下载K2
     */
    @GetMapping({"getK2"})
    @ApiOperation(value = "下载K2", notes = "买家在卖家上传K2后，下载K2")
    public ResponseBody getK2(@RequestParam UUID txId) {
        Transaction tx = this.transactionService.findTransactionById(txId.toString());
        if (tx == null || tx.getStatus() < 12) {
            log.info("非法操作，订单状态非法");
            return ResponseBody.fail("非法操作，订单状态非法");
        }

        Map<String, String> map = new HashMap<>();
        map.put("K2", tx.getK2());
        return ResponseBody.ok("K2", map);
    }

    /**
     * 订单确认
     *
     * @param txId UUID
     * @param bool boolean
     * @return 无
     * @apiNote 买家拿到K2，解密后调用此接口
     */
    @GetMapping({"confirm"})
    @ApiOperation(value = "订单确认", notes = "买家拿到K2，解密后调用此接口")
    public ResponseBody confirm(@RequestParam UUID txId, boolean bool) {
        Transaction tx = this.transactionService.findTransactionById(txId.toString());
        if (tx == null || tx.getStatus() != 12) {
            log.info("非法操作，订单状态非法");
            return ResponseBody.fail("非法操作，订单状态非法");
        }

        if (bool) {
            log.info("买家验证通过，订单完成");
            Transaction transaction = new Transaction(txId.toString(), 13);
            if (this.transactionService.updateTransactionById(transaction)) {
                log.info("订单状态更新成功");
                return ResponseBody.ok("买家验证通过，订单完成");
            }
            log.info("订单状态更新失败");
            return ResponseBody.fail("订单完成的订单状态更新失败");
        } else {
            log.info("买家验证未通过，发起仲裁");
            Transaction transaction = new Transaction(txId.toString(), 14);
            if (this.transactionService.updateTransactionById(transaction)) {
                log.info("订单状态更新成功");
                return ResponseBody.ok("买家验证未通过，发起仲裁");
            }
            log.info("订单状态更新失败");
            return ResponseBody.fail("发起仲裁的订单状态更新失败");
        }
    }


    public void download(String fileName, int fileType, HttpServletResponse response) {
        try {
            File file;
            // path是指想要下载的文件的路径
            if (fileType == 1) {
                file = this.fileService.getDataCipherFile(fileName);
            } else {
                file = this.fileService.getSampleFile(fileName);
            }
            log.info(file.getPath());
            // 获取文件名
            String filename = file.getName();
            // 获取文件后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
