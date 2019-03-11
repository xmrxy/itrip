package com.wu.dao;

import com.wu.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    /**
     * 添加订单信息
     * @param order
     * @return
     */
    int addOrder(Order order);

    /**
     * 获取订单最大的id
     * @return
     */
    int getOrderMaxId();

    /**
     * 根据退房时间和房间id查找订单数量
     */
    Integer getHouseCount(@Param("checkIn") String checkIn,
                      @Param("houseId") Integer houseId);

    /**
     * 查询全部订单信息
     * @return
     */
    List<Order> getOrder(Order order);

    /**
     * 根据order特定条件查询order表总数量
     * @param order
     * @return
     */
    int getOrderCount(Order order);

    /**
     * 根据id修改订单状态
     * @param orderId
     * @param status
     * @return
     */
    int updateOrderStatus( @Param("orderId") Integer orderId, @Param("status") Integer status);

    /**
     * 设定time,如果checkOut<=time,把 status改为2
     * @param time
     * @param status
     * @return
     */
    int updateOrderStatusByTime(@Param("time") String time, @Param("status") Integer status);

    /**
     * 根据orderNum修改status为已支付已入住
     * @param orderNum
     * @param status
     * @return
     */
    int updateOrderStatusByPay(@Param("orderNum") String orderNum, @Param("status") Integer status);

    /**
     * 根据id查询订单
     * @param orderId
     * @return
     */
    Order getOrderById(@Param("orderId") Integer orderId);

    /**
     * 根据订单编号查询该订单状态
     * @param orderNum
     * @return
     */
    int getOrderStatusByOrderNum(@Param("orderNum") String orderNum);

}
