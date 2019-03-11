package com.wu.service;

import com.wu.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
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
    int findOrderMaxId();


    /**
     * 根据退房时间和房间id查找订单数量
     */
    Integer findHouseCount(String checkIn,
                      Integer houseId);

    /**
     * 查询全部订单信息
     * @return
     */
    List<Order> findOrder(Order order);

    /**
     * 根据order特定条件查询order表总数量
     * @param order
     * @return
     */
    int findOrderCount(Order order);


    /**
     * 根据id修改订单状态e
     * @param orderId
     * @param status
     * @return
     */
    int updateOrderStatus(Integer orderId,Integer status);

    /**
     * 设定time,如果checkOut<=time,把 status改为2
     * @param time
     * @param status
     * @return
     */
    int updateOrderStatusByTime(String time,Integer status);
    /**
     * 根据orderNum修改status为已支付已入住
     * @param orderNum
     * @param status
     * @return
     */
    int updateOrderStatusByPay(String orderNum,Integer status);

    /**
     * 根据id查询订单
     * @param orderId
     * @return
     */
    Order findOrderById(Integer orderId);

    /**
     * 根据订单编号查询该订单状态
     * @param orderNum
     * @return
     */
    int findOrderStatusByOrderNum(String orderNum);


}
