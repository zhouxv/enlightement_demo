package com.enlightenment.demo.controller.goods;

import com.enlightenment.demo.dto.GoodsDTO;
import com.enlightenment.demo.dto.GoodsUpdateDTO;
import com.enlightenment.demo.entity.Goods;
import com.enlightenment.demo.service.daoservice.IGoodsService;
import com.enlightenment.demo.util.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.enlightenment.demo.dto.GoodsDTO.toGetGoods;

@Slf4j
@RestController
@Api(tags = "GoodsManagement 商品管理")
@RequestMapping({"goodsManagement"})
public class GoodsManagement {
    private final IGoodsService goodsService;

    public GoodsManagement(IGoodsService goodsService) {
        this.goodsService = goodsService;
    }

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


    @GetMapping({"getAll"})
    @ApiOperation(value = "商品检索", notes = "不传参检索所有，传参检索对应的字段")
    public ResponseBody getAll(@RequestParam(required = false) UUID goodsId, @RequestParam(required = false) UUID sellerId, @RequestParam(required = false) UUID dataSetId, @RequestParam(required = false) Integer contractType) {
        List<Goods> list = this.goodsService.findAllGoods(toGetGoods(goodsId, dataSetId, sellerId, contractType));
        return ResponseBody.ok("检索完成", list);
    }

    @PostMapping({"update"})
    @ApiOperation(value = "更新商品信息", notes = "更新商品信息")
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
