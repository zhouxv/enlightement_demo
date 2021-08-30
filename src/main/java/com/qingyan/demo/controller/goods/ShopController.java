package com.qingyan.demo.controller.goods;

import com.qingyan.demo.dto.ShopDetailedDTO;
import com.qingyan.demo.entity.DataSet;
import com.qingyan.demo.entity.Goods;
import com.qingyan.demo.service.daoservice.IDataSetService;
import com.qingyan.demo.service.daoservice.IGoodsService;
import com.qingyan.demo.util.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.qingyan.demo.entity.Goods.goodsListToShopDtoList;

/**
 * 商城浏览 controller.
 *
 * @author zhouxv 2021/7/23.
 */
@Slf4j
@Api(tags = "Shop 商城浏览")
@RequestMapping({"shop"})
@RestController
public class ShopController {
    private final IDataSetService dataSetService;
    private final IGoodsService goodsService;

    public ShopController(IDataSetService dataSetService, IGoodsService goodsService) {
        this.dataSetService = dataSetService;
        this.goodsService = goodsService;
    }

    /**
     * 商城浏览
     *
     * @param contractType 合约类型
     * @return
     * @apiNote 获取所有商品基本信息
     */
    @GetMapping({"getAll"})
    @ApiOperation(value = "商城浏览", notes = "获取所有商品基本信息")
    public ResponseBody getAll(Integer contractType) {
        Goods goods = new Goods();
        goods.setContracttype(contractType);
        List<Goods> goodsList = this.goodsService.findAllGoods(goods);
        return ResponseBody.ok("全部商品查询成功", goodsListToShopDtoList(goodsList));
    }

    /**
     * 查询具体的商品信息
     *
     * @param goodsId UUID 商品Id
     * @return
     * @apiNote 根据goodsId查询具体的商品信息
     */
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
