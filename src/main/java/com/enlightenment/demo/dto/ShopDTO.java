package com.enlightenment.demo.dto;

import com.enlightenment.demo.entity.DataSet;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/*
@Note：

@User：NineSun
@Time:2021/5/20   16:23
*/
@Data
@ApiModel(value = "ShopDTO", description = "商城浏览界面数据集DTO")
public class ShopDTO {
    @ApiModelProperty(value = "数据集Id")
    private String dataSetId;

    @ApiModelProperty(value = "数据集名称")
    private String dataSetName;

    @ApiModelProperty(value = "数据集名称")
    private Double priceHigh;

    @ApiModelProperty(value = "数据集名称")
    private Double priceLow;

    public static ShopDTO dataSetToShop(DataSet dataSet) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setDataSetId(dataSet.getDatasetid());
        shopDTO.setDataSetName(dataSet.getDatasetname());
        shopDTO.setPriceHigh(dataSet.getPricehigh());
        shopDTO.setPriceLow(dataSet.getPricelow());
        return shopDTO;
    }

    public static List<ShopDTO> dataSetListToShopDTOList(List<DataSet> list) {
        List<ShopDTO> dtoList = new LinkedList<>();
        for (DataSet dataSet : list) {
            dtoList.add(dataSetToShop(dataSet));
        }
        return dtoList;
    }
}
