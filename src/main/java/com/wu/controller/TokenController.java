package com.wu.controller;

import com.wu.pojo.User;
import com.wu.pojo.tokenVo.Dto;
import com.wu.pojo.tokenVo.TokenVO;
import com.wu.util.JsonUtil;
import com.wu.util.RedisUtil;
import com.wu.util.tokenutil.ErrorCode;
import com.wu.util.tokenutil.TokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Calendar;

@Controller
public class TokenController {

    @RequestMapping(value = "/readLocal")
    @ResponseBody
    public String readLocal(HttpServletRequest request) {
        Dto dto = new Dto();
        dto.setStatus("200");
        String token = request.getHeader("token");
        System.out.println("token===="+token);

        boolean existsToken = TokenUtil.existsToken(token);
        if (existsToken) {
            try {
                //置换token
                RedisUtil.getJedis().expire("token",60);

                String userJson = RedisUtil.getJedis().get(token);
                User user = JsonUtil.getObjectMapper().readValue(userJson, User.class);
                String newToken = TokenUtil.generateToken(request.getHeader("user-agent"), user);
                //保存token
                TokenUtil.saveToken(newToken, user);
                //创建tokenVo
                Calendar calendar = Calendar.getInstance();
                TokenVO tokenVO = new TokenVO();
                tokenVO.setToken(newToken);
                tokenVO.setGenTime(calendar.getTimeInMillis());
                tokenVO.setExpTime(calendar.getTimeInMillis() + 7200 * 1000);

                dto.setStatus("200");
                dto.setSuccess("true");
                dto.setMsg("Token置换成功");
                dto.setData(tokenVO);
                dto.setObj(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            dto.setErrorCode(ErrorCode.AUTH_REPLACEMENT_FAILED);
            dto.setSuccess("false");
            dto.setMsg("Token置换失败");
        }
        return JsonUtil.getJson(dto);
    }
}
