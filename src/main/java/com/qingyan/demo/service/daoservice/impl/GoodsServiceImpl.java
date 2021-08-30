package com.qingyan.demo.service.daoservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingyan.demo.entity.Goods;
import com.qingyan.demo.mapper.GoodsMapper;
import com.qingyan.demo.service.daoservice.IGoodsService;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    public List<Goods> findAllGoods(Goods goods) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>(goods);
        return this.list(wrapper);
    }

    @Override
    public Goods findGoodsById(String goodsId) {
        return this.getById(goodsId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createGoods(Goods goods) {
        return this.save(goods);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateGoodsById(Goods goods) {
        return this.updateById(goods);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteGoodsById(String goodsId) {
        return this.removeById(goodsId);
    }
}
