package com.qingyan.demo.dto;

import com.qingyan.demo.entity.Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
@Note：

@User：NineSun
@Time:2021/5/20   16:23
*/
@Data
@ApiModel(value = "ShopDTO", description = "商城浏览界面数据集DTO")
public class ShopDTO {
    /**
     * 商品Id
     */
    @ApiModelProperty(value = "商品Id")
    private String goodsId;

    /**
     * 数据集名称
     */
    @ApiModelProperty(value = "数据集名称")
    private String goodsName;

    /**
     * 数据集Id
     */
    @ApiModelProperty(value = "数据集Id")
    private String dataSetId;

    /**
     * 最高价
     */
    @ApiModelProperty(value = "最高价")
    private Double priceHigh;

    /**
     * 最低价
     */
    @ApiModelProperty(value = "最低价")
    private Double priceLow;

    /**
     * 合同类型
     */
    @ApiModelProperty(value = "合同类型")
    private Integer contractType;

    public ShopDTO(String goodsId, String goodsName, String dataSetId, Double priceHigh, Double priceLow, Integer contractType) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.dataSetId = dataSetId;
        this.priceHigh = priceHigh;
        this.priceLow = priceLow;
        this.contractType = contractType;
    }

    public ShopDTO(Goods goods) {
        this.goodsId = goods.getGoodsid();
        this.goodsName = goods.getGoodsname();
        this.dataSetId = goods.getDatasetid();
        this.priceHigh = goods.getPricehigh();
        this.priceLow = goods.getPricelow();
        this.contractType = goods.getContracttype();
    }
}
