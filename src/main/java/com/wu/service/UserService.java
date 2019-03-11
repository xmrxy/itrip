package com.wu.service;

import com.wu.pojo.User;
import org.apache.ibatis.annotations.Param;


public interface UserService {

    /**
     * 根据邮箱和密码查询一个用户
     * @param mail
     * @param password
     * @return
     */
    User findOneUserByMail(String mail,String password);

    /**
     * 根据邮箱和密码查询一个用户
     * @param phoneNum
     * @param password
     * @return
     */
    User findOneUserByPhone(String phoneNum,String password);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据mail查询有无已存在的邮箱
     * @param mail
     * @return
     */
    int existMail(String mail);

    /**
     * 根据电话查询有无已存在的电话
     * @param phone
     * @return
     */
    int existPhone(String phone);

    /**
     * 修改user信息
     * @param user
     * @return
     */
    int updateUserInfo(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserById(@Param("id") Integer id);
}
