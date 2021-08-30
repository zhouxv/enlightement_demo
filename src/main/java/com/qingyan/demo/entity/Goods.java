package com.qingyan.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qingyan.demo.dto.ShopDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity
 *
 * @author helit747@gmail.com
 * @date 2021-06-27 19:19:48
 */
@Data
@TableName("goods")
public class Goods {

    /**
     * 商品id
     */
    @TableId(value = "goodsId", type = IdType.ASSIGN_UUID)
    private String goodsid;

    /**
     * 商品名称
     */
    @TableField(value = "goodsName")
    private String goodsname;

    /**
     * 数据集Id
     */
    @TableField("dataSetId")
    private String datasetid;

    /**
     * 卖家Id
     */
    @TableField(value = "sellerId")
    private String sellerid;

    /**
     * 合同类型
     */
    @TableField("contractType")
    private Integer contracttype;


    /**
     * 高价格
     */
    @TableField("priceHigh")
    private Double pricehigh;

    /**
     * 低价格
     */
    @TableField("priceLow")
    private Double pricelow;

    /**
     * 评论
     */
    @TableField("remarks")
    private String remarks;

    public Goods() {
    }

    public static List<ShopDTO> goodsListToShopDtoList(List<Goods> goodsList) {
        List<ShopDTO> shopDTOList = new ArrayList<>();
        for (Goods goods : goodsList) {
            shopDTOList.add(new ShopDTO(goods));
        }
        return shopDTOList;
    }
}
