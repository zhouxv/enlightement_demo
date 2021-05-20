package com.enlightenment.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.enlightenment.demo.dto.DataSetDTO;
import lombok.Data;

/**
 * Entity
 *
 * @author zhouxv@gmail.com
 * @date 2021-05-19 11:22:11
 */
@Data
@TableName("data_set")
public class DataSet {

    /**
     * 数据集Id
     */
    @TableId(value = "dataSetId", type = IdType.ASSIGN_UUID)
    private String datasetid;

    /**
     * 数据集名称
     */
    @TableField("dataSetName")
    private String datasetname;

    /**
     * 数据集描述
     */
    @TableField("dataSetDescription")
    private String datasetdescription;

    /**
     * 卖家Id
     */
    @TableField("sellerId")
    private String sellerid;

    /**
     * 数据集最高价格
     */
    @TableField("priceHigh")
    private Double pricehigh;

    /**
     * 数据集最低价格
     */
    @TableField("priceLow")
    private Double pricelow;

    /**
     * 数据集哈希
     */
    @TableField("dataSetHash")
    private String datasethash;

    /**
     * 数据集签名
     */
    @TableField("dataSetSig")
    private String datasetsig;

    /**
     * 样本数据集哈希
     */
    @TableField("sampleHash")
    private String samplehash;

    /**
     * 样本数据集签名
     */
    @TableField("sampleSig")
    private String samplesig;

    public DataSet() {
    }

    public DataSet(DataSetDTO dataSetDTO) {
        this.datasetname = dataSetDTO.getDataSetName();
        this.datasetdescription = dataSetDTO.getDataSetDescription();
        this.sellerid = dataSetDTO.getSellerId().toString();
        this.pricehigh = dataSetDTO.getPriceHigh();
        this.pricelow = dataSetDTO.getPriceLow();
        this.datasethash = dataSetDTO.getDataSetHash();
        this.datasetsig = dataSetDTO.getDataSetSig();
        this.samplehash = dataSetDTO.getSampleHash();
        this.samplesig = dataSetDTO.getSampleSig();
    }
}
