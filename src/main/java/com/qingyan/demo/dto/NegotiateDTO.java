package com.qingyan.demo.dto;

import com.qingyan.demo.entity.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "NegotiateDTO", description = "合约信息")
public class NegotiateDTO {
    /**
     * 买家id
     */
    @ApiModelProperty(value = "买家id")
    private UUID buyerId;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private UUID goodsId;

    /**
     * 数据集价格
     */
    @ApiModelProperty(value = "数据集价格")
    private Double price;

    public Transaction toTransaction() {
        Transaction tx = new Transaction();
        tx.setGoodsid(this.goodsId.toString());
        tx.setBuyerid(this.buyerId.toString());
        tx.setPrice(this.price);
        return tx;
    }
}
