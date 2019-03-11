package com.wu.util;

import redis.clients.jedis.Jedis;

public class RedisUtil {
    //    配置redis
    private static final Jedis jedis=new Jedis("localhost");
    //设置redis密码
    static {
        jedis.auth("511300");
    }

    public  static Jedis getJedis(){
        return jedis;
    }
}
