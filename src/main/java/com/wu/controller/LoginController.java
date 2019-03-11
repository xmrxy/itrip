package com.wu.controller;


import com.wu.pojo.User;
import com.wu.pojo.tokenVo.Dto;
import com.wu.pojo.tokenVo.TokenVO;
import com.wu.service.UserService;
import com.wu.util.JsonUtil;
import com.wu.util.RedisUtil;
import com.wu.util.tokenutil.ErrorCode;
import com.wu.util.tokenutil.TokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Calendar;

@Controller

public class LoginController {

    @Resource
    private UserService userService;


    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = "/doLogin")
    @ResponseBody
    public String doLogin(@RequestParam(value = "mail",required = false) String mail,
                          @RequestParam(value = "password",required = true) String password,
                          @RequestParam(value = "phoneNum",required = false) String phoneNum,
                          HttpServletRequest request){
        byte[] passwordByte = Base64.getEncoder().encode(password.getBytes());
        Dto dto = new Dto();
        if (mail!=null&&!"".equals(mail)&&password!=null&&!"".equals(password)){
            //邮箱登录
            User user = null;
            try {
                user = userService.findOneUserByMail(mail, new String(passwordByte,"utf-8"));
            } catch (UnsupportedEncodingException e) {
                System.out.println("new String(passwordByte,'utf-8')异常");
                e.printStackTrace();
            }
            dto=loginSuccessToken(request, dto, user);
        }

        if (phoneNum!=null&&!"".equals(phoneNum)&&password!=null&&!"".equals(password)){
            //手机登录
            User user = null;
            try {
                user = userService.findOneUserByPhone(phoneNum, new String(passwordByte,"utf-8"));
            } catch (UnsupportedEncodingException e) {
                System.out.println("new String(passwordByte,'utf-8')异常");
                e.printStackTrace();
            }
            dto=loginSuccessToken(request, dto, user);
        }
        return JsonUtil.getJson(dto);
    }



    @RequestMapping(value = "/logOut")
    @ResponseBody
    public String logout(HttpServletRequest request) {
        Dto dto = new Dto();
        dto.setStatus("200");

        String token = request.getHeader("token");
        System.out.println("logOut:token=" + token);
        boolean existsToken = TokenUtil.existsToken(token);
        if (existsToken) {
            TokenUtil.delToken(token);
            dto.setMsg("注销成功");
            dto.setSuccess("true");
        } else {
            dto.setErrorCode(ErrorCode.AUTH_TOKEN_INVALID);
            dto.setSuccess("false");
            dto.setMsg("Token无效");
        }

        return JsonUtil.getJson(dto);
    }



    private Dto loginSuccessToken(HttpServletRequest request, Dto dto, User user) {
        if (user!=null){
//                session.setAttribute("user",user);
//                request.getSession().getServletContext().setAttribute("user",user);
            //登录成功,把user对象放到redis
            RedisUtil.getJedis().setex("user",7200,JsonUtil.getJson(user));

            //生成token
            System.out.println("请求头：" + request.getHeader("user-agent"));

            String token = TokenUtil.generateToken(request.getHeader("user-agent"),user);
            //保存token
            TokenUtil.saveToken(token, user);
            //创建tokenVo
            Calendar calendar = Calendar.getInstance();
            TokenVO tokenVO = new TokenVO();
            tokenVO.setToken(token);
            tokenVO.setGenTime(calendar.getTimeInMillis());
            tokenVO.setExpTime(calendar.getTimeInMillis() +7200 * 1000);

            dto.setStatus("200");
            dto.setSuccess("true");
            dto.setMsg("登录成功");
            dto.setData(tokenVO);
            user.setPassword("");
            dto.setObj(user);
            System.out.println("对象是："+dto.getObj());
        }else {
            //登录失败
            dto.setStatus("200");
            dto.setErrorCode(ErrorCode.AUTH_INVALID_LOGIN_OR_PASSWORD);
            dto.setSuccess("false");
            dto.setMsg("用户名或密码不正确!");
        }
        return dto;
    }

}
