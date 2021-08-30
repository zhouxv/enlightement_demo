package com.qingyan.demo.dto;

import com.qingyan.demo.entity.Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "GoodsUpdateDTO", description = "商品更新DTO")
public class GoodsUpdateDTO {
    /**
     * 商品Id
     */
    @ApiModelProperty(value = "商品Id")
    private UUID goodsId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
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

    public Goods toGoods() {
        Goods goods = new Goods();
        goods.setGoodsid(this.goodsId.toString());
        goods.setContracttype(this.contractType);
        goods.setPricehigh(this.priceHigh);
        goods.setPricelow(this.priceLow);
        goods.setRemarks(this.remarks);
        return goods;
    }
}
