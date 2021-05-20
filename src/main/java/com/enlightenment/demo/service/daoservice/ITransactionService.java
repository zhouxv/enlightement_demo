package com.enlightenment.demo.service.daoservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.enlightenment.demo.entity.Transaction;

import java.util.List;

/**
 * Service接口
 *
 * @author zhouxv@gmail.com
 * @date 2021-05-19 11:22:11
 */
public interface ITransactionService extends IService<Transaction> {

    /**
     * 查询（所有）
     *
     * @return List<Transaction>
     */
    List<Transaction> findAllTXBySellerId();

    /**
     * 根据卖家id查询（所有）处于某一状态的订单
     *
     * @return List<Transaction>
     */
    List<Transaction> findAllTXBySellerId(String sellerId, Integer status);

    /**
     * 根据买家id查询（所有）处于某一状态的订单
     *
     * @return List<Transaction>
     */
    List<Transaction> findAllTXByBuyerId(String buyerId, Integer status);

    /**
     * 根据主键Id，36位UUID查询列表
     *
     * @param orderId String
     * @return Transaction
     */
    Transaction findTransactionById(String orderId);


    /**
     * 新增
     *
     * @param order order
     */
    Boolean createTransaction(Transaction order);

    /**
     * 修改
     *
     * @param order order
     */
    Boolean updateTransactionById(Transaction order);

    /**
     * 删除
     *
     * @param orderId String
     */
    Boolean deleteTransactionById(String orderId);
}
