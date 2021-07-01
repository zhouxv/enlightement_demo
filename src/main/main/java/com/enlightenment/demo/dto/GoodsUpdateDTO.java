package com.enlightenment.demo.dto;

import com.enlightenment.demo.entity.Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "GoodsUpdateDTO", description = "商品更新DTO")
public class GoodsUpdateDTO {
    @ApiModelProperty(value = "商品Id")
    private UUID goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "合同类型")
    private Integer contractType;

    @ApiModelProperty(value = "最高价格")
    private Double priceHigh;

    @ApiModelProperty(value = "最低价格")
    private Double priceLow;

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
