package com.wu.service.impl;

import com.wu.dao.OrderMapper;
import com.wu.pojo.Order;
import com.wu.service.OrderService;
import com.wu.util.CronExpressionUtil;
import com.wu.util.QuartzJobManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private QuartzJobManager quartzJobManager;


    @Resource
    private OrderMapper orderMapper;
    @Override
    public int addOrder(Order order) {
        Map<String,Object> map=new HashMap<>();
        map.put("orderNum",order.getOrderNum());
        map.put("status",4);
        String cronExpression=CronExpressionUtil.getCronExpression();
        System.out.println(cronExpression);
        quartzJobManager.addJob(OrderJobDetailImpl.class,order.getOrderNum().toString(),"order",cronExpression,map);
        return orderMapper.addOrder(order);
    }

    @Override
    public int findOrderMaxId() {
        return orderMapper.getOrderMaxId();
    }

    @Override
    public Integer findHouseCount(String  checkIn, Integer houseId) {
        return orderMapper.getHouseCount(checkIn,houseId);
    }

    @Override
    public List<Order> findOrder(Order order) {
        return orderMapper.getOrder(order);
    }

    @Override
    public int findOrderCount(Order order) {
        return orderMapper.getOrderCount(order);
    }

    @Override
    public int updateOrderStatus(Integer orderId, Integer status) {
        return orderMapper.updateOrderStatus(orderId,status);
    }

    @Override
    public int updateOrderStatusByTime(String time, Integer status) {
        return orderMapper.updateOrderStatusByTime(time,status);
    }

    @Override
    public int updateOrderStatusByPay(String orderNum, Integer status) {
        return orderMapper.updateOrderStatusByPay(orderNum,status);
    }

    @Override
    public Order findOrderById(Integer orderId) {
        return orderMapper.getOrderById(orderId);
    }

    @Override
    public int findOrderStatusByOrderNum(String orderNum) {
        return orderMapper.getOrderStatusByOrderNum(orderNum);
    }
}
