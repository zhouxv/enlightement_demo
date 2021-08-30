package com.qingyan.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Entity
 *
 * @author helit747@gmail.com
 * @date 2021-06-27 19:19:48
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
     * 卖家Id
     */
    @TableField("sellerId")
    private String sellerid;

    /**
     * 数据集所有权
     */
    @TableField("ownership")
    private Integer ownership;

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
}
