package com.enlightenment.demo.dto;

import com.enlightenment.demo.entity.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "NegotiateDTO", description = "合约信息")
public class NegotiateDTO {
    @ApiModelProperty(value = "买家id")
    private UUID buyerId;

    @ApiModelProperty(value = "商品id")
    private UUID goodsId;

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
