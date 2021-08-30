package com.qingyan.demo.dto;

import com.qingyan.demo.entity.Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "GoodsDTO", description = "上架商品的信息")
public class GoodsDTO {

    /**
     * 数据集Id
     */
    @ApiModelProperty(value = "数据集Id")
    private UUID datasetId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 卖家id
     */
    @ApiModelProperty(value = "卖家id")
    private UUID sellerId;

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
     * 数据集所有权
     */
    @ApiModelProperty(value = "数据集所有权")
    private Integer ownership;

    public static Goods toGetGoods(UUID goodsId, UUID datasetId, UUID sellerId, Integer contractType) {
        Goods goods = new Goods();
        if (goodsId != null)
            goods.setGoodsid(goodsId.toString());
        if (datasetId != null)
            goods.setDatasetid(datasetId.toString());
        if (sellerId != null)
            goods.setSellerid(sellerId.toString());
        goods.setContracttype(contractType);
        return goods;
    }

    public Goods toGoods() {
        Goods goods = new Goods();
        goods.setGoodsname(this.goodsName);
        goods.setSellerid(this.sellerId.toString());
        goods.setDatasetid(this.datasetId.toString());
        goods.setContracttype(this.contractType);
        goods.setPricehigh(this.priceHigh);
        goods.setPricelow(this.priceLow);
        goods.setRemarks(this.remarks);
        return goods;
    }
}
