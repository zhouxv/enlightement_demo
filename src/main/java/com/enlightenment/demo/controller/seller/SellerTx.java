package com.enlightenment.demo.controller.seller;

import com.enlightenment.demo.dto.DataSetCipherDTO;
import com.enlightenment.demo.dto.NegotiateDTO;
import com.enlightenment.demo.dto.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@Api(tags = "SellerTx 卖家交易")
@RequestMapping({"sellerTx"})
public class SellerTx {

    @GetMapping({"getAllNegotiate"})
    @ApiOperation(value = "检索所有的协商请求", notes = "卖家传递自己的公钥，检索所有的协商")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "sellerPK", value = "卖家公钥", required = true)
    })
    public ResponseBody getAll(String sellerPK) {
        List<NegotiateDTO> list = new ArrayList<>();
        list.add(new NegotiateDTO());
        list.add(new NegotiateDTO());
        return ResponseBody.ok("检索完成", list);
    }

    @GetMapping({"negotiate"})
    @ApiOperation(value = "协商", notes = "卖家通过或者驳回协商提交")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "txId", value = "交易Id", required = true),
            @ApiImplicitParam(name = "bool", value = "协商结果，true为通过，false为驳回", required = true)
    })
    public ResponseBody negotiate(String txId, boolean bool) {
        if (bool) return ResponseBody.ok("协商通过", null);
        return ResponseBody.ok("协商请求驳回", null);
    }

//    @ApiOperation(value = "协商共享密钥outerKey", notes = "卖家生成对称密钥，使用买家公钥加密，将密文、密钥的哈希、签名传给后台")
//    @GetMapping({"outerKey"})
//    @ApiImplicitParams(value = {
//            @ApiImplicitParam(name = "cipher", value = "Enc(K(SB),Bpk)，使用买家公钥加密", required = true),
//            @ApiImplicitParam(name = "outerKeyHash", value = "SHA256(K(SB))", required = true),
//            @ApiImplicitParam(name = "outerKeySig", value = "Sig(SHA256(K(SB)))，使用卖家私钥签名", required = true)
//    })
//    public ResponseBody outerKey(String cipher, String outerKeyHash, String outerKeySig) {
//        return ResponseBody.ok("共享密钥已发送", null);
//    }

    @GetMapping({"verifyOuterKey"})
    @ApiOperation(value = "确认outerKey", notes = "卖家提交对称密钥的哈希和签名")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "txId", value = "交易Id", required = true),
            @ApiImplicitParam(name = "outerKeyHash", value = "outerKey的哈希", required = true),
            @ApiImplicitParam(name = "outerKeySig", value = "卖家对outerKey的签名", required = true)
    })
    public ResponseBody verifyOuterKey(String txId, String outerKeyHash, String outerKeySig) {

        return ResponseBody.ok("卖家outerKEy确认完成", null);
    }

    @PostMapping({"uploadDataSetCipher"})
    @ApiOperation(value = "上传数据集密文包", notes = "卖家提交数据集密文包，以及相应的哈希和签名")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "dataSetCipher", value = "上传的数据集加密包", required = true)
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
