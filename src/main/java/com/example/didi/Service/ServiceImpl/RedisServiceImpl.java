package com.example.didi.Service.ServiceImpl;

import com.example.didi.Service.RedisService;
import com.example.didi.tools.Redis.JedisWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("RedisService")
public class RedisServiceImpl implements RedisService {
    private final static String KEY_PREFIX = "selectRedisKey";
    private final static Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpl.class);
    JedisWrapper jedisWrapper;

    @Override
    public String getK(String key) {
        jedisWrapper = JedisWrapper.singleton();
        String k = KEY_PREFIX + key;
        String GetRedisKV = jedisWrapper.getKV(k);
        LOGGER.info("获取结果：" + GetRedisKV);
        return GetRedisKV;
    }

    @Override
    public String delKV(String key) {
        jedisWrapper = JedisWrapper.singleton();
        String k = KEY_PREFIX + key;
        if (jedisWrapper.getKV(k) != null) {
            jedisWrapper.removeKV(k);
            return "删除成功,查询结果" + jedisWrapper.getKV(k);
        }
        return "该key在redis中已不存在";
    }

    @Override
    public boolean putKV(String key, String value) {
        jedisWrapper = JedisWrapper.singleton();
        String k = KEY_PREFIX + key;
        boolean PutRedisKV = jedisWrapper.putNxKV(k, value);
        return PutRedisKV;
    }
}
