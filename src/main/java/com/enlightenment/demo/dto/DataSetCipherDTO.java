package com.enlightenment.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "DataSetCipherDTO", description = "数据集加密包证据")
public class DataSetCipherDTO {
    @ApiModelProperty(value = "交易Id")
    private String txId;

    @ApiModelProperty(value = "数据集密文哈希")
    private String dataSetCipherHash;

    @ApiModelProperty(value = "数据集密文签名")
    private String dataSetCipherSig;
}
