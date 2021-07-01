package com.enlightenment.demo.controller.goods;

import com.enlightenment.demo.dto.ShopDetailedDTO;
import com.enlightenment.demo.entity.DataSet;
import com.enlightenment.demo.entity.Goods;
import com.enlightenment.demo.service.daoservice.IDataSetService;
import com.enlightenment.demo.service.daoservice.IGoodsService;
import com.enlightenment.demo.util.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.enlightenment.demo.entity.Goods.goodsListToShopDtoList;


@Slf4j
@RestController
@Api(tags = "Shop 商城浏览")
@RequestMapping({"shop"})
public class ShopController {
    private final IDataSetService dataSetService;
    private final IGoodsService goodsService;

    public ShopController(IDataSetService dataSetService, IGoodsService goodsService) {
        this.dataSetService = dataSetService;
        this.goodsService = goodsService;
    }

    @GetMapping({"getAll"})
    @ApiOperation(value = "商城浏览", notes = "获取所有商品基本信息")
    public ResponseBody getAll(Integer contractType) {
        Goods goods = new Goods();
        goods.setContracttype(contractType);
        List<Goods> goodsList = this.goodsService.findAllGoods(goods);
        return ResponseBody.ok("全部商品查询成功", goodsListToShopDtoList(goodsList));
    }


    @GetMapping({"getOne"})
    @ApiOperation(value = "根据goodsId查询具体的商品信息", notes = "根据goodsId查询具体的商品信息")
    public ResponseBody getOne(@RequestParam UUID goodsId) {
        Goods goods = this.goodsService.findGoodsById(goodsId.toString());
        if (goods != null) {
            log.info("goodsId为<" + goodsId + ">的商品查询成功");
            DataSet dataSet = this.dataSetService.findDataSetById(goods.getDatasetid());
            return ResponseBody.ok("商品详情查询成功", new ShopDetailedDTO(goods, dataSet));
        }
        log.info("查询不到指定id的商品");
        return ResponseBody.fail("查询不到指定id的商品");
    }

}
