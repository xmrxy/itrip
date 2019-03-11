package com.wu.util.tokenutil;

import com.wu.pojo.User;
import com.wu.util.JsonUtil;
import com.wu.util.RedisUtil;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TokenUtil {


    public static String generateToken(String userAgent, User user) {
        StringBuffer sbf = new StringBuffer();
        sbf.append("token:");
        boolean flag = UserAgentUtil.checkAgent(userAgent);//判断客户端类型
        if (flag) {
            sbf.append("mobile-");
        } else {
            sbf.append("pc-");
        }
        sbf.append(MD5.getMd5(user.getPassword(), 32));
        sbf.append("-");
        sbf.append(user.getId());
        sbf.append("-");
        sbf.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        sbf.append("-");
        sbf.append(MD5.getMd5(userAgent, 6));
        return sbf.toString();
    }

    public static boolean saveToken(String token, User user) {
        String json = JsonUtil.getJson(user);//把用户对象转换成Json
        if (token.startsWith("token:pc")) {
            RedisUtil.getJedis().setex(token,7200,json);
//            redisTemplate.opsForValue().set(token,json,7200,TimeUnit.SECONDS);
            //redisUtil.set(token, jacksonObjectMapper.writeValueAsString(user),7200);
        } else {
//            redisTemplate.opsForValue().set(token, json);
            RedisUtil.getJedis().set(token,json);
        }
        return true;
    }

    public static boolean existsToken(String key) {
       return  RedisUtil.getJedis().exists(key);

    }

    public static Long delToken(String key) {
        return RedisUtil.getJedis().del(key);
    }
}
