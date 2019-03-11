package com.wu.controller;


import com.wu.pojo.User;
import com.wu.service.UserService;
import com.wu.util.JsonUtil;
import com.wu.util.RedisUtil;
import com.wu.util.SmsUtil;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class RegisterController {

    private String randomNum = "";

    @Resource
    private UserService userService;

    @Resource
    private JavaMailSender javaMailSender;

    @RequestMapping(value = "/registerPage")
    public String loginPage() {
        return "register";
    }

    @RequestMapping(value = "/registerSuccess")
    public String registerSuccess() {
        return "registersuccess";
    }


    @RequestMapping(value = "/registerLoss")
    public String registerLoss() {
        return "registerloss";
    }

    @RequestMapping(value = "/getCodeByPhone")
    @ResponseBody
    public String getYanZhengCodeByPhone(@RequestParam(value = "phoneNum",required = true)String phoneNum){
        Map<String,Object> map=new HashMap<>();
        int j = userService.existPhone(phoneNum);
        if (j>0){
            //电话已存在
            map.put("msg","该号码已注册");
            return JsonUtil.getJson(map);
        }
        Random random = new Random();
        randomNum="";
        for (int i=0;i<6;i++){
            randomNum=randomNum+random.nextInt(10);
        }
        SmsUtil.sendSms(phoneNum,randomNum,"1");
        map.put("msg","验证码已发送,请在1分钟内完成注册");
        String json = JsonUtil.getJson(map);
        RedisUtil.getJedis().setex("randomNum",60,randomNum);
        return json;
    }

    @RequestMapping(value = "/phoneRegister")
    public String phoneRegister(User user,
                                @RequestParam(value = "yanZhengCode", required = true) String code) {
        if (code.equals(RedisUtil.getJedis().get("randomNum"))&&!"".equals(code)) {
            byte[] passwordByte = Base64.getEncoder().encode(user.getPassword().getBytes());
            try {
                user.setPassword(new String(passwordByte,"utf-8"));
            } catch (UnsupportedEncodingException e) {
                System.out.println("new String(passwordByte,'utf-8')异常");
                e.printStackTrace();
            }
            int i = userService.addUser(user);
            if (i > 0) {
                //注册成功
                return "redirect:/registerSuccess";
            } else {
                //注册失败
                return "redirect:/registerLoss";
            }
        } else {
            //验证码不正确
            return "redirect:/registerLoss";
        }
    }


    @RequestMapping(value = "/getYanZhengCode")
    @ResponseBody
    public String getYanZhengCode(@RequestParam(value = "mail", required = true) String mail) {
        Map<String, Object> map = new HashMap<>();
        String json = null;
        int j = userService.existMail(mail);
        if (j > 0) {
            //已存在邮箱
            map.put("msg", "邮箱已存在");
            return JsonUtil.getJson(map);
        }
        Random random1 = new Random();
        randomNum="";
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("xmrxy@sina.com");
            helper.setTo(mail);
            helper.setSubject("爱旅行注册");

            for (int i = 0; i < 6; i++) {
                int num = random1.nextInt(10);
                randomNum = randomNum + num;
            }

            helper.setText("注册验证码：" + randomNum);
            javaMailSender.send(mimeMessage);
            RedisUtil.getJedis().setex("randomNum",60,randomNum);
            map.put("msg", "已发送验证码,请在1分钟内完成注册");
            json = JsonUtil.getJson(map);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("邮箱发送失败");
        }
        return json;
    }

    @RequestMapping(value = "/mailRegister")
    public String register(User user, @RequestParam(value = "yanZhengCode", required = true) String code) {

        if (code.equals(RedisUtil.getJedis().get("randomNum"))&&!"".equals(code)) {
            int i = userService.addUser(user);
            if (i > 0) {
                //注册成功
                return "redirect:/registerSuccess";
            } else {
                //注册失败
                return "redirect:/registerLoss";
            }
        } else {
            //验证码不正确
            return "redirect:/registerLoss";
        }

    }

}
