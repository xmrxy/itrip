package com.wu.dao;

import com.wu.pojo.CheckInUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckInUserMapper {
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
    List<CheckInUser> getCheckInUserByOrderId(@Param("orderId") Integer orderId);
}
