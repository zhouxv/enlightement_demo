package com.qingyan.demo.dto;

import com.qingyan.demo.entity.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "OuterKeyDTO", description = "OuterKey相关信息实体类")
public class OuterKeyDTO {
    /**
     * 订单Id
     */
    @ApiModelProperty(value = "订单Id")
    private UUID txId;

    /**
     * 对称密钥
     */
    @ApiModelProperty(value = "对称密钥")
    private String outerKey;

    /**
     * 对称密钥哈希
     */
    @ApiModelProperty(value = "对称密钥哈希")
    private String outerKeyHash;

    /**
     * 数据集价格
     */
    @ApiModelProperty(value = "对称密钥签名")
    private String outerKeySig;

    public OuterKeyDTO() {
    }

    public OuterKeyDTO(UUID txId, String outerKey, String outerKeyHash, String outerKeySig) {
        this.txId = txId;
        this.outerKey = outerKey;
        this.outerKeyHash = outerKeyHash;
        this.outerKeySig = outerKeySig;
    }

    public Transaction toSellerTransaction() {
        Transaction tx = new Transaction();
        tx.setTxid(this.txId.toString());
        tx.setOuterkey(this.outerKey);
        tx.setSelleroutkeydigest(this.outerKeyHash);
        tx.setSelleroutkeysig(this.outerKeySig);
        tx.setStatus(4);
        return tx;
    }

    public Transaction toBuyerTransaction() {
        Transaction tx = new Transaction();
        tx.setTxid(this.txId.toString());
        tx.setBuyeroutkeydigest(this.outerKeyHash);
        tx.setBuyseroutkeysig(this.outerKeySig);
        tx.setStatus(6);
        return tx;
    }

}
