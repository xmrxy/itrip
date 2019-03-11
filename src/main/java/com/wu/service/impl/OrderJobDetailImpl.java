package com.wu.service.impl;

import com.wu.service.OrderService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;


public class OrderJobDetailImpl implements Job {

    @Resource
    private OrderService orderService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //方法的作用，订单超时，取消订单
        String orderNum = (String) jobExecutionContext.getMergedJobDataMap().get("orderNum");
        Integer status = (Integer) jobExecutionContext.getMergedJobDataMap().get("status");
        if (orderService.findOrderStatusByOrderNum(orderNum)==3){
            orderService.updateOrderStatusByPay(orderNum.toString(),status);
        }
    }
}
