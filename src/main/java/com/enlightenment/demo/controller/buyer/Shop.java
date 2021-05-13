package com.enlightenment.demo.controller.buyer;

import com.enlightenment.demo.dto.DataSetDTO;
import com.enlightenment.demo.dto.QueryRequest;
import com.enlightenment.demo.dto.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@Api(tags = "Shop 商城浏览")
@RequestMapping({"shop"})
public class Shop {

    @GetMapping({"getAll"})
    @ApiOperation(value = "商城浏览", notes = "获取所有数据集基本信息")
    public ResponseBody getAll(QueryRequest request) {
        List<DataSetDTO> list = new ArrayList<>();
        list.add(new DataSetDTO());
        list.add(new DataSetDTO());

        return ResponseBody.ok("查询成功", list);
    }

    @GetMapping({"getOne"})
    @ApiOperation(value = "商品详情", notes = "根据数据集的哈希检索商品的所有信息")
    public ResponseBody getOne(String dataSetHash) {
        DataSetDTO dataSetDTO = new DataSetDTO();
        return ResponseBody.ok("商品详情查询成功", dataSetDTO);
    }

}
