package com.qingyan.demo.dto;

import com.qingyan.demo.entity.DataSet;
import com.qingyan.demo.entity.Goods;
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
    /**
     * 商品Id
     */
    @ApiModelProperty(value = "商品Id")
    private String goodsId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 合同类型
     */
    @ApiModelProperty(value = "合同类型")
    private Integer contractType;

    /**
     * 最高价格
     */
    @ApiModelProperty(value = "最高价格")
    private Double priceHigh;

    /**
     * 最低价格
     */
    @ApiModelProperty(value = "最低价格")
    private Double priceLow;

    /**
     * 商品备注
     */
    @ApiModelProperty(value = "商品备注")
    private String remarks;

    /**
     * 卖家Id
     */
    @ApiModelProperty(value = "卖家Id")
    private String sellerId;

    /**
     * 数据集Id
     */
    @ApiModelProperty(value = "数据集Id")
    private String dataSetId;

    /**
     * 数据集名称
     */
    @ApiModelProperty(value = "数据集名称")
    private String dataSetName;

    /**
     * 数据集描述
     */
    @ApiModelProperty(value = "数据集描述")
    private String dataSetDescription;

    /**
     * 数据集哈希
     */
    @ApiModelProperty(value = "数据集哈希")
    private String dataSetHash;

    /**
     * 数据集签名
     */
    @ApiModelProperty(value = "数据集签名")
    private String dataSetSig;

    /**
     * 样本数据集哈希
     */
    @ApiModelProperty(value = "样本数据集哈希")
    private String sampleHash;

    /**
     * 样本数据集签名
     */
    @ApiModelProperty(value = "样本数据集签名")
    private String sampleSig;

    /**
     * 数据集所有权
     */
    @ApiModelProperty(value = "数据集所有权")
    private Integer ownership;

    public ShopDetailedDTO() {
    }

    public ShopDetailedDTO(Goods goods, DataSet dataSet) {
        this.goodsId = goods.getGoodsid();
        this.goodsName = goods.getGoodsname();
        this.contractType = goods.getContracttype();
        this.priceHigh = goods.getPricehigh();
        this.priceLow = goods.getPricelow();
        this.remarks = goods.getRemarks();
        this.sellerId = goods.getSellerid();
        this.dataSetId = goods.getDatasetid();

        this.dataSetName = dataSet.getDatasetname();
        this.dataSetDescription = dataSet.getDatasetdescription();
        this.dataSetHash = dataSet.getDatasethash();
        this.dataSetSig = dataSet.getDatasetsig();
        this.sampleHash = dataSet.getDatasethash();
        this.sampleSig = dataSet.getDatasetsig();
        this.ownership = dataSet.getOwnership();
    }
}
