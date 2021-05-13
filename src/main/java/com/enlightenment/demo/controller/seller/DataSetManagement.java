package com.enlightenment.demo.controller.seller;

import com.enlightenment.demo.dto.DataSetDTO;
import com.enlightenment.demo.dto.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@Api(tags = "DataSetManagement 卖家数据集管理")
@RequestMapping({"dataSetManagement"})
public class DataSetManagement {

    @PostMapping({"claim"})
    @ApiOperation(value = "数据集所有权声明接口", notes = "传入数据集基本信息、数据集和sample的哈希签名，以及上传sample文件")
    public ResponseBody claim(@ApiParam(value = "上传的样本数据") @RequestPart MultipartFile sampleData, @RequestPart DataSetDTO dataSetDTO) {
        System.out.println(dataSetDTO);
        return ResponseBody.ok("数据权声明成功", null);
    }


}
