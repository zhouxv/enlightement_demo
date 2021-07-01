package com.enlightenment.demo.dto;

import com.enlightenment.demo.entity.DataSet;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
@ApiModel(value = "DataSetDTO", description = "声明数据集所有权的所有信息")
public class DataSetDTO {
    @ApiModelProperty(value = "卖家Id,UUID")
    private UUID sellerId;

    @ApiModelProperty(value = "数据集名称")
    private String dataSetName;

    @ApiModelProperty(value = "数据集描述")
    private String dataSetDescription;

    @ApiModelProperty(value = "数据集哈希")
    private String dataSetHash;

    @ApiModelProperty(value = "数据集签名")
    private String dataSetSig;

    @ApiModelProperty(value = "样本数据集哈希")
    private String sampleHash;

    @ApiModelProperty(value = "样本数据集签名")
    private String sampleSig;

    public static DataSet toGetDataSet(UUID datasetId, UUID userId) {
        DataSet dataSet = new DataSet();
        if (dataSet != null)
            dataSet.setDatasetid(datasetId.toString());
        if (userId != null)
            dataSet.setSellerid(userId.toString());
        return dataSet;
    }

    public DataSet toGetDataSet() {
        DataSet dataSet = new DataSet();
        dataSet.setSellerid(this.sellerId.toString());
        dataSet.setDatasetname(this.dataSetName);
        dataSet.setDatasetdescription(this.dataSetDescription);
        dataSet.setDatasethash(this.dataSetHash);
        dataSet.setDatasetsig(this.dataSetSig);
        dataSet.setSamplehash(this.sampleHash);
        dataSet.setSamplesig(this.sampleSig);
        dataSet.setOwnership(1);
        return dataSet;
    }
}
