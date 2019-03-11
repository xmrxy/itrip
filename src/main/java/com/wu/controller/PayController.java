package com.wu.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.wu.pojo.CheckInUser;
import com.wu.pojo.Order;
import com.wu.pojo.User;
import com.wu.service.CheckInUserService;
import com.wu.service.OrderService;
import com.wu.util.AlipayConfig;
import com.wu.util.JsonUtil;
import com.wu.util.RedisUtil;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PayController {
    @Resource
    private OrderService orderService;

    @Resource
    private CheckInUserService checkInUserService;

    @RequestMapping(value = "/payPage")
    public ModelAndView payPage(@Validated Order order,
                                ModelAndView modelAndView,
                                @RequestParam(value = "trueName") String[] trueName,
                                @RequestParam(value = "idNum") String[] idNum,
                                @RequestParam(value = "phoneNum") String[] phoneNum) {

        //设置订单名称
        modelAndView.addObject("hotelName", order.getHotelName());
        //设置订单号
        Long orderNum = System.currentTimeMillis();
        RedisUtil.getJedis().setex("orderNum", 61, orderNum.toString());
        modelAndView.addObject("orderNum", orderNum);
        //设置价格
        modelAndView.addObject("price", order.getPrice());
        //商品描述
        modelAndView.addObject("description", order.getHotelName() + "房间预订");
        try {
            User user = JsonUtil.getObjectMapper().readValue(RedisUtil.getJedis().get("user"), User.class);
            Order orderBean = new Order(order.getCheckIn(), order.getCheckOut(), order.getHotelName(), order.getHouseCount(), order.getPrice(), order.getContactName(), order.getPhone(), order.getMail(), user.getId(), order.getHouseName(), order.getHouseId(), new Date(), 3, orderNum.toString());
            int i = orderService.addOrder(orderBean);

            int orderMaxId = orderService.findOrderMaxId();
            //住客信息
            CheckInUser checkInUser=new CheckInUser();
            for (int j = 0; j < trueName.length; j++) {
                checkInUser.setTrueName(trueName[j]);
                checkInUser.setIdNum(idNum[j]);
                checkInUser.setOrderId(orderMaxId);
                checkInUser.setPhoneNum(phoneNum[j]);
                checkInUser.setUserId(user.getId());
                checkInUserService.addCheckInUser(checkInUser);
            }
            if (i > 0) {
                modelAndView.setViewName("pay");
                return modelAndView;
            }
        } catch (Exception e) {
            System.out.println("时期转换错误");
            e.printStackTrace();
        }

        modelAndView.setViewName("pay");
        return modelAndView;
    }

    @RequestMapping(value = "/goPay")
    public ModelAndView goPay(ModelAndView modelAndView, @RequestParam(value = "orderId", required = false) String orderId) {
        Order order = orderService.findOrderById(Integer.parseInt(orderId));
        //设置订单名称
        modelAndView.addObject("hotelName", order.getHotelName());
        //设置订单号
        RedisUtil.getJedis().setex("orderNum", 61, order.getOrderNum());
        modelAndView.addObject("orderNum", order.getOrderNum());
        //设置价格
        modelAndView.addObject("price", order.getPrice());
        //商品描述
        modelAndView.addObject("description", order.getHotelName() + "房间预订");
        modelAndView.setViewName("pay");
        return modelAndView;
    }


    @RequestMapping(value = "/doPay")
    public void pay(HttpServletResponse response,
                    @RequestParam(value = "WIDout_trade_no") String orderNum,
                    @RequestParam(value = "WIDsubject") String hotelName,
                    @RequestParam(value = "WIDtotal_amount") String price, HttpSession session) {
        // 商户订单号
        String out_trade_no = orderNum;
        // 订单名称
        String subject = hotelName;
        // 付款金额
        String total_amount = price;
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        alipay_request.setBizModel(model);
        // 设置异步通知地址
        alipay_request.setNotifyUrl(AlipayConfig.notify_url);
        // 设置同步地址
        alipay_request.setReturnUrl(AlipayConfig.return_url);

        // form表单生产
        String form = "";
        try {
            // 调用SDK生成表单
            form = client.pageExecute(alipay_request).getBody();
            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    ;

    @RequestMapping(value = "/notify_url")
    public void notifyPage(HttpServletRequest request, HttpServletResponse response) {
        try {
            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
                params.put(name, valueStr);
            }
            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //商户订单号

            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            //支付宝交易号

            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
            //计算得出通知验证结果
            //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
            boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");

            if (verify_result) {//验证成功
                //////////////////////////////////////////////////////////////////////////////////////////
                //请在这里加上商户的业务逻辑程序代码

                //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

                if (trade_status.equals("TRADE_FINISHED")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                    //如果有做过处理，不执行商户的业务程序

                    //注意：
                    //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                    //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
                } else if (trade_status.equals("TRADE_SUCCESS")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                    //如果有做过处理，不执行商户的业务程序

                    //注意：
                    //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
                }

                //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
                response.getWriter().println("success");    //请不要修改或删除
                //////////////////////////////////////////////////////////////////////////////////////////
            } else {//验证失败
                response.getWriter().println("fail");

            }
        } catch (Exception e) {
            System.out.println("notifyPage发生错误");
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/return_url")
    public String paySuccessPage(HttpSession session) {
        int i = orderService.updateOrderStatusByPay(RedisUtil.getJedis().get("orderNum"), 1);
        return "paysuccess";
    }


}
