package com.qingyan.demo.service.daoservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingyan.demo.entity.Aftermarket;

import java.util.List;

/**
 * Service接口
 *
 * @date 2021-06-27 19:19:48
 */
public interface IAftermarketService extends IService<Aftermarket> {

    List<Aftermarket> findAllAftermarket(Aftermarket aftermarket);

    /**
     * 查询（所有）
     *
     * @return List<Aftermarket>
     */
    List<Aftermarket> findAllAftermarket();

    /**
     * 根据主键Id，36位UUID查询列表
     *
     * @param aftermarketId String
     * @return Aftermarket
     */
    Aftermarket findAftermarketById(String aftermarketId);


    /**
     * 新增
     *
     * @param aftermarket aftermarket
     */
    Boolean createAftermarket(Aftermarket aftermarket);

    /**
     * 修改
     *
     * @param aftermarket aftermarket
     */
    Boolean updateAftermarketById(Aftermarket aftermarket);

    /**
     * 删除
     *
     * @param aftermarketId String
     */
    Boolean deleteAftermarketById(String aftermarketId);
}
