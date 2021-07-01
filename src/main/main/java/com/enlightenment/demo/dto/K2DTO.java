package com.enlightenment.demo.dto;

import com.enlightenment.demo.entity.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "K2DTO", description = "卖家上传K2DTO")
public class K2DTO {
    @ApiModelProperty(value = "订单Id,UUID")
    private UUID txId;

    @ApiModelProperty(value = "K2")
    private String K2;

    public K2DTO() {
    }

    public K2DTO(UUID txId, String k2) {
        this.txId = txId;
        K2 = k2;
    }

    public Transaction toTransaction() {
        Transaction tx = new Transaction();
        tx.setTxid(this.txId.toString());
        tx.setK2(this.K2);
        tx.setStatus(12);
        return tx;
    }
}
