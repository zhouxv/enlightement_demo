package com.qingyan.demo.service.daoservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingyan.demo.entity.User;
import com.qingyan.demo.mapper.UserMapper;
import com.qingyan.demo.service.daoservice.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service实现
 *
 * @author helit747@gmail.com
 * @date 2021-06-27 19:19:48
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public List<User> findAllUser() {
        return this.list();
    }

    @Override
    public User findUserById(String userId) {
        return this.getById(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createUser(User user) {
        return this.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUserById(User user) {
        return this.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteUserById(String userId) {
        return this.removeById(userId);
    }

    @Override
    public Boolean isValidUser(String userId) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserid, userId);
        return this.count(wrapper) == 1;
    }

}
