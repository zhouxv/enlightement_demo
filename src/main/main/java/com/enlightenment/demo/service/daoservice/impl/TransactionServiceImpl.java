package com.enlightenment.demo.service.daoservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.enlightenment.demo.entity.Transaction;
import com.enlightenment.demo.mapper.TransactionMapper;
import com.enlightenment.demo.service.daoservice.ITransactionService;
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
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements ITransactionService {

    @Override
    public List<Transaction> findAllTX(Transaction transaction) {
        LambdaQueryWrapper<Transaction> wrapper = new LambdaQueryWrapper<>(transaction);
        return this.list(wrapper);
    }

    @Override
    public List<Transaction> findAllTXBySellerId(String sellerId, Integer status) {
        LambdaQueryWrapper<Transaction> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Transaction::getSellerid, sellerId).eq(status != null, Transaction::getStatus, status);
        return this.list(wrapper);
    }


    @Override
    public List<Transaction> findAllTXByBuyerId(String buyerId, Integer status) {
        LambdaQueryWrapper<Transaction> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Transaction::getBuyerid, buyerId).eq(status != null, Transaction::getStatus, status);
        return this.list(wrapper);
    }

    @Override
    public Transaction findTransactionById(String txId) {
        return this.getById(txId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createTransaction(Transaction tx) {
        tx.setStatus(1);
        return this.save(tx);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTransactionById(Transaction tx) {
        return this.updateById(tx);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteTransactionById(String txId) {
        return this.removeById(txId);
    }
}
