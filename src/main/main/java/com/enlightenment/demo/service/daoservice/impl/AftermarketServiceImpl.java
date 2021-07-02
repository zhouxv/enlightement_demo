package com.enlightenment.demo.service.daoservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enlightenment.demo.entity.Aftermarket;
import com.enlightenment.demo.mapper.AftermarketMapper;
import com.enlightenment.demo.service.daoservice.IAftermarketService;
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
public class AftermarketServiceImpl extends ServiceImpl<AftermarketMapper, Aftermarket> implements IAftermarketService {

    @Override
    public List<Aftermarket> findAllAftermarket() {
        return this.list();
    }

    @Override
    public List<Aftermarket> findAllAftermarket(Aftermarket aftermarket) {
        LambdaQueryWrapper<Aftermarket> wrapper = new LambdaQueryWrapper<>(aftermarket);
        return this.list(wrapper);
    }

    @Override
    public Aftermarket findAftermarketById(String aftermarketId) {
        return this.getById(aftermarketId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createAftermarket(Aftermarket aftermarket) {
        aftermarket.setReturnstatus(1);
        return this.save(aftermarket);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateAftermarketById(Aftermarket aftermarket) {
        return this.updateById(aftermarket);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteAftermarketById(String aftermarketId) {
        return this.removeById(aftermarketId);
    }
}
