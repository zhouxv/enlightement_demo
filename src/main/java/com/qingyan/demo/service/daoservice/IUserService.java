package com.qingyan.demo.service.daoservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingyan.demo.entity.User;

import java.util.List;

/**
 * Service接口
 *
 * @author helit747@gmail.com
 * @date 2021-06-27 19:19:48
 */
public interface IUserService extends IService<User> {
    /**
     * 查询（所有）
     *
     * @return List<User>
     */
    List<User> findAllUser();

    /**
     * 根据主键Id，36位UUID查询列表
     *
     * @param userId String
     * @return User
     */
    User findUserById(String userId);


    /**
     * 新增
     *
     * @param user user
     */
    Boolean createUser(User user);

    /**
     * 修改
     *
     * @param user user
     */
    Boolean updateUserById(User user);

    /**
     * 删除
     *
     * @param userId String
     */
    Boolean deleteUserById(String userId);


    /**
     * 查询是否是合法用户
     *
     * @param userId String
     */
    Boolean isValidUser(String userId);
}
