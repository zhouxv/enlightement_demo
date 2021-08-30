package com.qingyan.demo.dto;

import com.qingyan.demo.entity.Aftermarket;
import com.qingyan.demo.entity.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "NegotiateDTO", description = "合约信息")
public class AftermarketDTO {
    /**
     * 订单Id
     */
    @ApiModelProperty(value = "订单Id")
    private UUID transactionId;


    /**
     * 仲裁理由
     */
    @ApiModelProperty(value = "仲裁理由")
    private String returnReason;

    public Aftermarket TransactionToAftermarket(Transaction transaction) {
        Aftermarket aftermarket = new Aftermarket();
        aftermarket.setDatasetid(transaction.getDatasetid());
        aftermarket.setBuyerid(transaction.getBuyerid());
        aftermarket.setSellerid(transaction.getSellerid());
        aftermarket.setGoodsid(transaction.getGoodsid());
        aftermarket.setReturnreason(this.returnReason);
        aftermarket.setTransactionid(transaction.getTxid());
        return aftermarket;
    }
}
