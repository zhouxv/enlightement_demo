package com.enlightenment.demo.dto;

import com.enlightenment.demo.entity.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "NegotiateDTO", description = "合约信息")
public class NegotiateDTO {
    @ApiModelProperty(value = "订单id")
    private UUID txId;

    @ApiModelProperty(value = "卖家id")
    private UUID sellerId;

    //买家信息
    @ApiModelProperty(value = "买家id")
    private UUID buyerId;

    // 数据集信息
    @ApiModelProperty(value = "数据集id")
    private UUID dataSetId;

    @ApiModelProperty(value = "数据集价格")
    private Double price;

    public Transaction toTransaction() {
        Transaction tx = new Transaction();
        tx.setDatasetid(this.dataSetId.toString());
        tx.setSellerid(this.sellerId.toString());
        tx.setBuyerid(this.buyerId.toString());
        tx.setPrice(this.price);
        return tx;
    }
}
