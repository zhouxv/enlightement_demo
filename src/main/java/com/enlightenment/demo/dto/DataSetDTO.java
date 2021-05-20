package com.enlightenment.demo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "DataSetDTO", description = "声明数据集所有权的所有信息")
public class DataSetDTO {
    @ApiModelProperty(value = "卖家Id,UUID")
    private UUID sellerId;

    @ApiModelProperty(value = "数据集名称")
    private String dataSetName;

    @ApiModelProperty(value = "数据集描述")
    private String dataSetDescription;

    @ApiModelProperty(value = "数据集最低价格")
    private double priceLow;

    @ApiModelProperty(value = "数据集最高价格")
    private double priceHigh;

    @ApiModelProperty(value = "数据集哈希")
    private String dataSetHash;

    @ApiModelProperty(value = "数据集签名")
    private String dataSetSig;

    @ApiModelProperty(value = "样本数据集哈希")
    private String sampleHash;

    @ApiModelProperty(value = "样本数据集签名")
    private String sampleSig;
}
