package com.enlightenment.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Entity
 *
 * @author helit747@gmail.com
 * @date 2021-06-27 19:19:48
 */
@Data
@TableName("transaction")
public class Transaction {

    /**
     * 订单Id，UUID
     */
    @TableId(value = "txId", type = IdType.ASSIGN_UUID)
    private String txid;

    /**
     * 买家-数据集密文-哈希
     */
    @TableField("buyerDataSetCipherDigest")
    private String buyerdatasetcipherdigest;

    /**
     * 买家-数据集密文-签名
     */
    @TableField("buyerDataSetCipherSig")
    private String buyerdatasetciphersig;

    /**
     * 买家⁯Id
     */
    @TableField("buyerId")
    private String buyerid;

    /**
     * 买家-OuterKey-哈希
     */
    @TableField("buyerOutKeyDigest")
    private String buyeroutkeydigest;

    /**
     * 买家-OuterKey-签名
     */
    @TableField("buyserOutKeySig")
    private String buyseroutkeysig;

    /**
     * 数据集Id
     */
    @TableField("dataSetId")
    private String datasetid;

    /**
     * 商品Id
     */
    @TableField("goodsId")
    private String goodsid;

    /**
     * K2
     */
    @TableField("K2")
    private String k2;

    /**
     * outerKey
     */
    @TableField("outerKey")
    private String outerkey;

    /**
     * 价格
     */
    @TableField("price")
    private Double price;

    /**
     * 卖家-数据集密文-哈希
     */
    @TableField("sellerDataSetCipherDigest")
    private String sellerdatasetcipherdigest;

    /**
     * 卖家-数据集密文-签名
     */
    @TableField("sellerDataSetCipherSig")
    private String sellerdatasetciphersig;

    /**
     * 卖家Id
     */
    @TableField("sellerId")
    private String sellerid;

    /**
     * 卖家-OuterKey-哈希
     */
    @TableField("sellerOutKeyDigest")
    private String selleroutkeydigest;

    /**
     * 卖家-OuterKey-签名
     */
    @TableField("sellerOutKeySig")
    private String selleroutkeysig;

    /**
     * 订单状态
     */
    @TableField("status")
    private Integer status;


    @TableField("contractType")
    private Integer contracttype;

    public Transaction() {
    }

    public Transaction(String txId, Integer status) {
        this.txid = txId;
        this.status = status;
    }

    public Transaction(String txId, Double price, Integer status) {
        this.txid = txId;
        this.price = price;
        this.status = status;
    }
}
