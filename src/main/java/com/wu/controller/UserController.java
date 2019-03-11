package com.wu.controller;


import com.wu.pojo.User;
import com.wu.service.UserService;
import com.wu.util.JsonUtil;
import com.wu.util.RedisUtil;
import com.wu.util.SmsUtil;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class UserController {

    private String phoneRandomNum = "";
    private String mailRandomNum = "";


    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/center")
    public String centerPage(HttpServletRequest request) {
        return "center";
    }


    @RequestMapping(value = "/userInfoPage")
    public String userInfoPage() {
        return "userInfo";
    }

    @RequestMapping(value = "/modifyUserInfo")
    public String userInfoPage(User user, Model model) {
        int i = userService.updateUserInfo(user);
        if (i > 0) {
            RedisUtil.getJedis().setex("user", 7200 * 1000, JsonUtil.getJson(user));
        } else {
            model.addAttribute("errorMsg", "修改信息失败");
        }
        RedisUtil.getJedis().del("phoneRandomNum","mailRandomNum");
        return "redirect:/userInfoPage";
    }

    @RequestMapping(value = "/getModifyUserInfo")
    @ResponseBody
    public String getModifyUserInfo() {
        String userJson = RedisUtil.getJedis().get("user");
        User user = null;
        try {
            user = JsonUtil.getObjectMapper().readValue(userJson, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        user.setPassword("");
        map.put("user", user);
        return JsonUtil.getJson(map);
    }

    @RequestMapping(value = "/getCodeByUpdatePhone")
    @ResponseBody
    public String getCodeByUpdatePhone(@RequestParam(value = "phoneNum") String phoneNum) {
        Random random = new Random();
        Map<String, Object> map = new HashMap<>();
        phoneRandomNum = "";
        for (int i = 0; i < 6; i++) {
            phoneRandomNum = phoneRandomNum + random.nextInt(10);
        }
        SmsUtil.sendSms(phoneNum, phoneRandomNum, "1");
        map.put("phoneCode", phoneRandomNum);
        RedisUtil.getJedis().setex("phoneRandomNum", 300, phoneRandomNum);
        return JsonUtil.getJson(map);
    }

    @RequestMapping(value = "/getEmailCodeByUpdatePhone")
    @ResponseBody
    public String getEmailCodeByUpdatePhone(@RequestParam(value = "mailAddress") String mailAddress) {
        Random random1 = new Random();
        mailRandomNum = "";
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("xmrxy@sina.com");
            helper.setTo(mailAddress);
            helper.setSubject("爱旅行修改邮箱地址");

            for (int i = 0; i < 6; i++) {
                int num = random1.nextInt(10);
                mailRandomNum = mailRandomNum + num;
            }

            helper.setText("爱旅行修改邮箱地址：" + mailRandomNum);
            javaMailSender.send(mimeMessage);
            RedisUtil.getJedis().setex("mailRandomNum", 300, mailRandomNum);
            Map<String, Object> map = new HashMap<>();
            map.put("emailCode", mailRandomNum);
            return JsonUtil.getJson(map);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("修改邮箱地址验证码发送失败");
        }
        return null;
    }


    @RequestMapping(value = "/test/{date}")
    public String test(@PathVariable(value = "date") String date){
        System.out.println("date:"+date);
        return "test";
    }
}
