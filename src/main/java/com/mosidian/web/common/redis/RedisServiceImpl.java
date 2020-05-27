package com.mosidian.web.common.redis;

import cn.hutool.db.nosql.redis.RedisDS;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class RedisServiceImpl implements RedisService {

    Jedis jedis = RedisDS.create().getJedis();

    @Override
    public boolean save(String key, String value, int expireTime) {
        String setex = jedis.setex(key,expireTime,value);
        if (setex.equals("OK")){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void remove(String key) {
       jedis.del(key);
    }

    @Override
    public boolean exists(String key) {
        return jedis.exists(key);
    }

    @Override
    public String get(String key) {
        return jedis.get(key);
    }

}
