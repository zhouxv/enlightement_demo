package com.qingyan.demo.dto;

import com.qingyan.demo.entity.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "DataSetCipherDTO", description = "数据集加密包证据")
public class DataSetCipherDTO {
    /**
     * 交易Id
     */
    @ApiModelProperty(value = "交易Id")
    private UUID txId;

    /**
     * 数据集密文哈希
     */
    @ApiModelProperty(value = "数据集密文哈希")
    private String dataSetCipherHash;

    /**
     * 数据集密文签名
     */
    @ApiModelProperty(value = "数据集密文签名")
    private String dataSetCipherSig;

    public DataSetCipherDTO() {
    }

    public DataSetCipherDTO(UUID txId, String dataSetCipherHash, String dataSetCipherSig) {
        this.txId = txId;
        this.dataSetCipherHash = dataSetCipherHash;
        this.dataSetCipherSig = dataSetCipherSig;
    }

    public Transaction toSellerTransaction() {
        Transaction tx = new Transaction();
        tx.setTxid(this.txId.toString());
        tx.setSellerdatasetcipherdigest(this.dataSetCipherHash);
        tx.setSellerdatasetciphersig(this.dataSetCipherSig);
        tx.setStatus(7);
        return tx;
    }

    public Transaction toBuyerTransaction() {
        Transaction tx = new Transaction();
        tx.setTxid(this.txId.toString());
        tx.setBuyerdatasetcipherdigest(this.dataSetCipherHash);
        tx.setBuyerdatasetciphersig(this.dataSetCipherSig);
        tx.setStatus(8);
        return tx;
    }
}
