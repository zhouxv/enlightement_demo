package com.qingyan.demo.service.daoservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingyan.demo.entity.DataSet;

import java.util.List;

/**
 * Service接口
 *
 * @date 2021-06-27 19:19:48
 */
public interface IDataSetService extends IService<DataSet> {

    /**
     * 查询（所有）
     *
     * @return List<DataSet>
     */
    List<DataSet> findAllDataSet();

    /**
     * 查询（所有）
     *
     * @return List<DataSet>
     */
    List<DataSet> findAllDataSet(DataSet dataSet);

    /**
     * 查询（所有）
     *
     * @return List<DataSet>
     */
    List<DataSet> findAllDataSetByUserId(String userId);

    /**
     * 根据主键Id，36位UUID查询列表
     *
     * @param dataSetId String
     * @return DataSet
     */
    DataSet findDataSetById(String dataSetId);


    Boolean exists(String dataSetHash);

    /**
     * 新增
     *
     * @param dataSet dataSet
     */
    Boolean createDataSet(DataSet dataSet);

    /**
     * 修改
     *
     * @param dataSet dataSet
     */
    Boolean updateDataSetById(DataSet dataSet);

    /**
     * 删除
     *
     * @param dataSetId String
     */
    Boolean deleteDataSetById(String dataSetId);

}
