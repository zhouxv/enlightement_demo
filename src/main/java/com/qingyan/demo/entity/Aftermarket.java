package com.qingyan.demo.entity;


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
@TableName("aftermarket")
public class Aftermarket {

    /**
     * 退货Id
     */
    @TableId(value = "aftermarketId", type = IdType.ASSIGN_UUID)
    private String aftermarketid;

    /**
     * 数据集Id
     */
    @TableField("dataSetId")
    private String datasetid;

    /**
     * 买家id
     */
    @TableField("buyerId")
    private String buyerid;

    /**
     * 卖家id
     */
    @TableField("sellerId")
    private String sellerid;

    /**
     * 商品Id
     */
    @TableField("goodsId")
    private String goodsid;

    /**
     * 订单Id
     */
    @TableField("transactionId")
    private String transactionid;


    /**
     * 退货理由
     */
    @TableField("returnReason")
    private String returnreason;

    /**
     * 退货状态
     */
    @TableField("returnStatus")
    private Integer returnstatus;

    public Aftermarket() {
    }
}
