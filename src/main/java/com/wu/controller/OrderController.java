package com.wu.controller;

import com.wu.pojo.Order;
import com.wu.pojo.User;
import com.wu.service.OrderService;
import com.wu.util.JsonUtil;
import com.wu.util.PageBean;
import com.wu.util.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping(value = "/getOrder")
    @ResponseBody
    public String findOrder(@RequestParam(value = "hotelName",required = false)String hotelName,
                            @RequestParam(value = "contactName",required = false)String contactName,
                            @RequestParam(value = "status",required = false) String status,
                            @RequestParam(value = "pageIndex",required = false) String pageIndex,
                            HttpServletRequest request) {
        String userJson = RedisUtil.getJedis().get(request.getHeader("token"));
        if (userJson==null){
            Map<String,Object> map=new HashMap<>();
            map.put("msg","false");
            return JsonUtil.getJson(map);
        }
        User user = null;
        try {
            user = JsonUtil.getObjectMapper().readValue(userJson, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Order order=new Order();
        if (status!=null||"".equals(status)){
            order.setStatus(Integer.parseInt(status));
        }
        order.setHotelName(hotelName);
        order.setContactName(contactName);
        order.setUserId(user.getId());
        int orderCount = orderService.findOrderCount(order);
        PageBean<Order> pageBean=new PageBean<>();
        //设置页码容量
        pageBean.setTotalCount(orderCount);

        pageBean.setPageSize(5);
        //设置起始行
        if (pageIndex==null||"".equals(pageIndex)){
            pageIndex="1";
        }
        if (pageIndex!=null){
            if (Integer.parseInt(pageIndex)<=0){
                pageIndex="1";
            }
            if (Integer.parseInt(pageIndex)>=pageBean.getTotalPage()){
                pageIndex=pageBean.getTotalPage().toString();

            }
        }

        pageBean.setPageIndex(Integer.parseInt(pageIndex));
        int startRow = pageBean.getStartRow(pageBean.getPageIndex());

        order.setStarRow(startRow);
        order.setPageSize(pageBean.getPageSize());

        List<Order> orders = orderService.findOrder(order);
        pageBean.setList(orders);
        return JsonUtil.getJson(pageBean);
    }


    @RequestMapping(value = "/changeStatus")
    public String updateOrderStatus(@RequestParam(value = "orderId") Integer orderId,
                                    @RequestParam(value = "status") Integer status){
        int i = orderService.updateOrderStatus(orderId, status);
        Map<String,Object> map=new HashMap<>();
        if (i>0){
            map.put("msg","修改成功");
            return JsonUtil.getJson(map);
        }else {
            map.put("msg","修改失败");
            return JsonUtil.getJson(map);
        }
    }

}
