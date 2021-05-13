package com.enlightenment.demo.controller.buyer;

import com.enlightenment.demo.dto.DataSetCipherDTO;
import com.enlightenment.demo.dto.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@Api(tags = "BuyerTx 买家交易")
@RequestMapping({"buyerTx"})
public class BuyerTx {

    @ApiOperation(value = "买家开始协商", notes = "买家点击交易按钮，进入协商页面，点击提交，调用此接口")
    @GetMapping({"negotiate"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "buyerPK", value = "买家公钥", required = true),
            @ApiImplicitParam(name = "dataSetHash", value = "卖家公钥", required = true),
            @ApiImplicitParam(name = "price", value = "定价", required = true)
    })
    public ResponseBody negotiate(String buyerPK, String dataSetHash, String price) {
        Map<String, String> map = new HashMap<>();
        map.put("txId", "xxxxxxxx");

        return ResponseBody.ok("开始协商", map);
    }

//    @ApiOperation(value = "协商共享密钥outerKey", notes = "根据交易Id获取共享密钥密文包")
//    @GetMapping({"outerKey"})
//    @ApiImplicitParams(value = {
//            @ApiImplicitParam(name = "cipher", value = "Enc(K(SB),Bpk)，使用买家公钥加密", required = true)
//    })
//    public ResponseBody getOuterKey(String txId) {
//        Map<String,String> map=new HashMap<>();
//        map.put("cipher","xxxxxxxx");
//
//        return ResponseBody.ok("共享密钥已发送", map);
//    }

    @GetMapping({"verifyOuterKey"})
    @ApiOperation(value = "确认outerKey", notes = "买家提交对称密钥的哈希和签名")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "txId", value = "交易Id", required = true),
            @ApiImplicitParam(name = "outerKeyHash", value = "outerKey的哈希", required = true),
            @ApiImplicitParam(name = "outerKeySig", value = "买家对outerKey的签名", required = true)
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
            @ApiImplicitParam(name = "txId", value = "交易Id", required = true),
            @ApiImplicitParam(name = "bool", value = "决定结果，true为继续交易，进入付款流程，false为终止交易", required = true)
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
