package com.enlightenment.demo.service.daoservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.enlightenment.demo.entity.Goods;

import java.util.List;

/**
 * Service接口
 *
 * @date 2021-06-27 19:19:48
 */
public interface IGoodsService extends IService<Goods> {
    /**
     * 查询（所有）
     *
     * @return List<Goods>
     */
    List<Goods> findAllGoods(Goods goods);

    /**
     * 根据主键Id，36位UUID查询列表
     *
     * @param goodsId String
     * @return Goods
     */
    Goods findGoodsById(String goodsId);


    /**
     * 新增
     *
     * @param goods goods
     */
    Boolean createGoods(Goods goods);

    /**
     * 修改
     *
     * @param goods goods
     */
    Boolean updateGoodsById(Goods goods);

    /**
     * 删除
     *
     * @param goodsId String
     */
    Boolean deleteGoodsById(String goodsId);
}
