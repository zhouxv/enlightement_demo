package com.qingyan.demo.controller.goods;

import com.qingyan.demo.dto.GoodsDTO;
import com.qingyan.demo.dto.GoodsUpdateDTO;
import com.qingyan.demo.entity.Goods;
import com.qingyan.demo.service.daoservice.IGoodsService;
import com.qingyan.demo.util.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.qingyan.demo.dto.GoodsDTO.toGetGoods;


/**
 * 商品管理 controller.
 *
 * @author zhouxv 2021/7/22.
 */
@Slf4j
@Api(tags = "GoodsManagement 商品管理")
@RequestMapping({"goodsManagement"})
@RestController
public class GoodsManagement {
    private final IGoodsService goodsService;

    public GoodsManagement(IGoodsService goodsService) {
        this.goodsService = goodsService;
    }

    /**
     * 卖家上架某数据集
     *
     * @param goodsDTO GoodsDTO
     * @return
     * @apiNote 卖家上架某数据集
     */
    @ApiOperation(value = "上架某数据集", notes = "")
    @PostMapping({"upShelf"})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "goodsDTO", value = "商品信息", required = true, dataTypeClass = GoodsDTO.class)
    })
    public ResponseBody upShelf(@RequestBody GoodsDTO goodsDTO) {
        if (goodsDTO.getOwnership() != 1) {
            log.info("上架数据集失败,权限不足");
            return ResponseBody.fail("上架数据集失败,权限不足");
        }
        Goods goods = goodsDTO.toGoods();
        if (this.goodsService.createGoods(goods)) {
            log.info("上架dataSetId <" + goods.getDatasetid() + ">商品，goodsId为 " + goods.getGoodsid());
            return ResponseBody.ok("上架数据集成功", goods);
        }

        log.info("上架dataSetId <" + goods.getDatasetid() + ">商品失败");
        return ResponseBody.fail("上架数据集失败");
    }

    /**
     * 商品检索
     *
     * @param goodsId      UUID 商品Id
     * @param sellerId     UUID 商品Id
     * @param dataSetId    UUID 数据集Id
     * @param contractType Integer 合约类型
     * @return
     * @apiNote 不传参检索所有，传参检索对应的字段
     */
    @GetMapping({"getAll"})
    @ApiOperation(value = "商品检索", notes = "不传参检索所有，传参检索对应的字段")
    public ResponseBody getAll(@RequestParam(required = false) UUID goodsId, @RequestParam(required = false) UUID sellerId, @RequestParam(required = false) UUID dataSetId, @RequestParam(required = false) Integer contractType) {
        List<Goods> list = this.goodsService.findAllGoods(toGetGoods(goodsId, dataSetId, sellerId, contractType));
        return ResponseBody.ok("检索完成", list);
    }

    /**
     * 卖家更新商品信息
     *
     * @param goodsUpdateDTO GoodsUpdateDTO
     * @return
     * @apiNote 卖家更新商品信息
     */
    @PostMapping({"update"})
    @ApiOperation(value = "卖家更新商品信息", notes = "卖家更新商品信息")
    public ResponseBody update(@RequestBody GoodsUpdateDTO goodsUpdateDTO) {
        if (goodsUpdateDTO.getGoodsId() == null) return ResponseBody.fail("goodsId不能为空");
        Goods goods = goodsUpdateDTO.toGoods();

        if (this.goodsService.updateGoodsById(goods)) {
            log.info("goodsId为<" + goods.getGoodsid() + ">的商品更新成功");
            return ResponseBody.ok("更新成功");
        }

        log.info("goodsId为<" + goods.getGoodsid() + ">的商品更新失败");
        return ResponseBody.fail("更新失败");
    }

    /**
     * 删除商品，下架商品
     *
     * @param goodsId UUID
     * @return
     * @apiNote 卖家删除商品，下架商品
     */
    @DeleteMapping({"delete"})
    @ApiOperation(value = "删除商品，下架商品", notes = "删除商品，下架商品")
    public ResponseBody delete(@RequestParam UUID goodsId) {
        if (goodsId == null) return ResponseBody.fail("goodsId不能为空");

        if (this.goodsService.deleteGoodsById(goodsId.toString())) {
            log.info("goodsId为<" + goodsId + ">的商品删除成功");
            return ResponseBody.ok("删除成功");
        }

        log.info("goodsId为<" + goodsId + ">的商品删除失败");
        return ResponseBody.fail("删除失败");
    }

}
