package com.qingyan.demo.service.daoservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingyan.demo.entity.DataSet;
import com.qingyan.demo.mapper.DataSetMapper;
import com.qingyan.demo.service.daoservice.IDataSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service实现
 *
 * @date 2021-06-27 19:19:48
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DataSetServiceImpl extends ServiceImpl<DataSetMapper, DataSet> implements IDataSetService {

    @Override
    public List<DataSet> findAllDataSet() {
        return this.list();
    }

    @Override
    public List<DataSet> findAllDataSet(DataSet dataSet) {
        LambdaQueryWrapper<DataSet> wrapper = new LambdaQueryWrapper<>(dataSet);
        return this.list(wrapper);
    }

    @Override
    public List<DataSet> findAllDataSetByUserId(String userId) {
        LambdaQueryWrapper<DataSet> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DataSet::getSellerid, userId);
        return this.list(wrapper);
    }

    @Override
    public DataSet findDataSetById(String dataSetId) {
        return this.getById(dataSetId);
    }

    @Override
    public Boolean exists(String dataSetHash) {
        LambdaQueryWrapper<DataSet> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DataSet::getDatasethash, dataSetHash);
        return this.count(wrapper) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createDataSet(DataSet dataSet) {
        return this.save(dataSet);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateDataSetById(DataSet dataSet) {
        return this.updateById(dataSet);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteDataSetById(String dataSetId) {
        return this.removeById(dataSetId);
    }

}
