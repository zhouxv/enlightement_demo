package com.enlightenment.demo.controller.goods;

import com.enlightenment.demo.dto.DataSetDTO;
import com.enlightenment.demo.entity.DataSet;
import com.enlightenment.demo.service.daoservice.IDataSetService;
import com.enlightenment.demo.service.daoservice.IUserService;
import com.enlightenment.demo.service.otherservice.FileService;
import com.enlightenment.demo.util.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.enlightenment.demo.dto.DataSetDTO.toGetDataSet;


@Slf4j
@RestController
@Api(tags = "DataSetManagement 用户数据集管理")
@RequestMapping({"dataSetManagement"})
public class DataSetManagement {

    private final FileService fileService;
    private final IDataSetService dataSetService;
    private final IUserService userService;

    public DataSetManagement(FileService fileService, IDataSetService dataSetService, IUserService userService) {
        this.fileService = fileService;
        this.dataSetService = dataSetService;
        this.userService = userService;
    }


    @PostMapping({"claim"})
    @ApiOperation(value = "数据集所有权声明接口", notes = "传入数据集基本信息、数据集和sample的哈希签名，以及上传sample文件")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "sampleData", value = "上传的样本数据", required = true, dataTypeClass = MultipartFile.class),
            @ApiImplicitParam(name = "dataSetDTO", value = "上传的数据集信息", required = true, dataTypeClass = DataSetDTO.class)
    })
    public ResponseBody claim(@RequestPart MultipartFile sampleData, @RequestPart DataSetDTO dataSetDTO) throws Exception {
        if (!this.userService.isValidUser(dataSetDTO.getSellerId().toString())) {
            log.info("非法用户");
            return ResponseBody.fail("非法用户");
        }
        log.info("合法的卖家用户");

        if (this.dataSetService.exists(dataSetDTO.getDataSetHash())) {
            log.info("数据集已存在");
            return ResponseBody.fail("数据集已存在，数据集声明失败");
        }
        log.info("数据库中不存在该数据集");


        //        if (!isHashLegal(sampleData, dataSetDTO.getSampleHash())) {
        //            log.info("样本数据集提交文件hash验证未通过");
        //            return ResponseBody.fail("样本数据集提交文件hash验证未通过");
        //        }
        //        log.info("样本数据集提交文件hash验证通过");

        // TODO: 2021/5/20 样本数据集签名验证

        // TODO: 2021/5/20 数据集hash和签名验证

        if (!this.fileService.uploadSampleData(sampleData, dataSetDTO.getSampleHash())) {
            log.info("样本数据集存储失败，数据权声明失败");
            return ResponseBody.fail("样本数据集存储失败，数据权声明失败");
        }
        log.info("样本数据集存储成功");

        DataSet dataSet = dataSetDTO.toGetDataSet();
        if (!this.dataSetService.createDataSet(dataSet)) {
            log.info("dataSet添加失败，数据权声明失败");
            return ResponseBody.fail("dataSet添加失败，数据权声明失败");
        }

        log.info("数据权声明成功");
        Map<String, String> map = new HashMap<>();
        map.put("dataSetId", dataSet.getDatasetid());
        return ResponseBody.ok("数据权声明成功", map);
    }

    @GetMapping({"getAll"})
    @ApiOperation(value = "根据传入的字段检索", notes = "可以不传，默认检索所有")
    public ResponseBody getAllByUserId(@RequestParam(required = false) UUID datasetId, @RequestParam(required = false) UUID userId) {
        List<DataSet> dataSetList = this.dataSetService.findAllDataSet(toGetDataSet(datasetId, userId));
        return ResponseBody.ok("全部商品查询成功，无分页", dataSetList);
    }


    // TODO: 2021/6/30 删除指定数据集接口


}
