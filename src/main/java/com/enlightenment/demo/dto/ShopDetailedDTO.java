package com.enlightenment.demo.dto;

import com.enlightenment.demo.entity.DataSet;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
@Note：

@User：NineSun
@Time:2021/5/20   16:46
*/
@Data
@ApiModel(value = "ShopDetailedDTO", description = "商品详情页数据集详情")
public class ShopDetailedDTO {
    @ApiModelProperty(value = "卖家Id")
    private String sellerId;

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

    public ShopDetailedDTO() {
    }

    public ShopDetailedDTO(DataSet dataSet) {
        this.sellerId = dataSet.getSellerid();
        this.dataSetName = dataSet.getDatasetname();
        this.dataSetDescription = dataSet.getDatasetdescription();
        this.priceLow = dataSet.getPricelow();
        this.priceHigh = dataSet.getPricehigh();
        this.dataSetHash = dataSet.getDatasethash();
        this.dataSetSig = dataSet.getDatasetsig();
        this.sampleHash = dataSet.getSamplehash();
        this.sampleSig = dataSet.getSamplesig();
    }
}
