package com.wu.util;

import com.wu.service.OrderService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Configurable
@EnableScheduling
public class ScheduledUtil {

    @Resource
    private  OrderService orderService;


//    @Scheduled(fixedRate = 1000 * 30)
//    public void reportCurrentTime(){
//        System.out.println ("Scheduling Tasks Examples: The time is now " + dateFormat ().format (new Date()));
//    }

    //每天12执行一次
    @Scheduled(cron = "0 0 12 * * ? ")
    public void reportCurrentByCron(){
        orderService.updateOrderStatusByTime(dateFormat1().format(new Date()),2);
        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date ()));
    }

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }

    private SimpleDateFormat dateFormat1(){
        return new SimpleDateFormat ("yyyy-MM-dd");
    }

}
