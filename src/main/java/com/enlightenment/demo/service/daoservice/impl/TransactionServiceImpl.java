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
 * @author zhouxv@gmail.com
 * @date 2021-05-19 11:22:11
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements ITransactionService {
    @Override
    public List<Transaction> findAllTXBySellerId() {
        return this.list();
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
    public Transaction findTransactionById(String orderId) {
        return this.getById(orderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createTransaction(Transaction order) {
        order.setStatus(1);
        return this.save(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTransactionById(Transaction order) {
        return this.updateById(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteTransactionById(String orderId) {
        return this.removeById(orderId);
    }
}
