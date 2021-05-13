package com.enlightenment.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Negotiate", description = "协商请求")
public class NegotiateDTO {
    @ApiModelProperty(value = "买家公钥")
    private String buyerPK;

    @ApiModelProperty(value = "买家名称")
    private String buyerName;

    @ApiModelProperty(value = "数据集名称")
    private String dataSetName;

    @ApiModelProperty(value = "数据集哈希")
    private String dataSetHash;

    @ApiModelProperty(value = "交易Id")
    private String txId;

    @ApiModelProperty(value = "数据集最低价格")
    private double priceLow;

    @ApiModelProperty(value = "数据集最高价格")
    private double priceHigh;

    @ApiModelProperty(value = "协商价格")
    private double price;
}
