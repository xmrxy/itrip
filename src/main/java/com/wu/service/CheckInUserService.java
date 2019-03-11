package com.wu.service;

import com.wu.pojo.CheckInUser;

import java.util.List;

public interface CheckInUserService {
    /**
     * 添加住客信息
     * @param checkInUser
     * @return
     */
    int addCheckInUser(CheckInUser checkInUser);


    /**
     * 根据订单id查询联系人
     * @param orderId
     * @return
     */
    List<CheckInUser> findCheckInUserByOrderId(Integer orderId);
}
