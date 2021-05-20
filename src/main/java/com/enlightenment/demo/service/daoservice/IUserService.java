package com.enlightenment.demo.service.daoservice;


import com.baomidou.mybatisplus.extension.service.IService;
import com.enlightenment.demo.entity.User;

import java.util.List;

/**
 * Service接口
 *
 * @author zhouxv@gmail.com
 * @date 2021-05-19 11:22:11
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
     * 删除
     *
     * @param userId String
     */
    Boolean isSeller(String userId);
}
