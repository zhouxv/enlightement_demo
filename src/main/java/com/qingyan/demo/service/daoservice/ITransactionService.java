package com.qingyan.demo.service.daoservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingyan.demo.entity.Transaction;

import java.util.List;

/**
 * Service接口
 *
 * @date 2021-06-27 19:19:48
 */
public interface ITransactionService extends IService<Transaction> {

    List<Transaction> findAllTX(Transaction transaction);

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
     * @param txId String
     * @return Transaction
     */
    Transaction findTransactionById(String txId);

    /**
     * 新增
     *
     * @param tx Transaction
     */
    Boolean createTransaction(Transaction tx);

    /**
     * 修改
     *
     * @param tx Transaction
     */
    Boolean updateTransactionById(Transaction tx);

    /**
     * 删除
     *
     * @param txId String
     */
    Boolean deleteTransactionById(String txId);
}
