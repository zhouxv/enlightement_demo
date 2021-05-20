package com.enlightenment.demo.controller.buyer;

import com.enlightenment.demo.entity.DataSet;
import com.enlightenment.demo.service.daoservice.IDataSetService;
import com.enlightenment.demo.util.QueryRequest;
import com.enlightenment.demo.util.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@Api(tags = "Shop 商城浏览")
@RequestMapping({"shop"})
public class ShopController {
    private final IDataSetService dataSetService;

    public ShopController(IDataSetService dataSetService) {
        this.dataSetService = dataSetService;
    }

    @GetMapping({"getAll"})
    @ApiOperation(value = "商城浏览", notes = "获取所有数据集基本信息")
    public ResponseBody getAll(QueryRequest request) {
        List<DataSet> dataSetList = this.dataSetService.findAllDataSet();
        return ResponseBody.ok("全部商品查询成功，无分页", dataSetList);
    }

    /**
     * 这里有修改,改为根据数据集id查询所有信息
     *
     * @param dataSetId
     * @return
     */
    @GetMapping({"getOne"})
    @ApiOperation(value = "商品详情", notes = "这里有修改,根据哈希检索,修改为根据id(linearId)查")
    public ResponseBody getOne(String dataSetId) {
        DataSet dataSet = this.dataSetService.getById(dataSetId);
        if (dataSet != null) {
            log.info("查询成功");
            return ResponseBody.ok("商品详情查询成功", dataSet);
        }
        log.info("查询不到指定id的数据集");
        return ResponseBody.fail("查询不到指定id的数据集");
    }

}
