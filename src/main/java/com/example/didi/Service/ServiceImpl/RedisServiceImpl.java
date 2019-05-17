package com.example.didi.Service.ServiceImpl;

import com.example.didi.Service.RedisService;
import com.example.didi.domain.entity.RedisEntity;
import com.example.didi.domain.mapper.RedisMapper;
import com.example.didi.enums.RedisEnum;
import com.example.didi.tools.Redis.JedisWrapper;
import com.example.didi.tools.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("RedisService")
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisMapper redisMapper;
    ValidateUtils validateUtils;
    private final static String KEY_PREFIX = "selectRedisKey";
    private final static Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpl.class);
    JedisWrapper jedisWrapper;

    @Override
    public String getK(String key) {
        jedisWrapper = JedisWrapper.singleton();
        String k = KEY_PREFIX + key;
        LOGGER.info("param: key={}", k);
        String GetRedisKV = jedisWrapper.getKV(k);
        LOGGER.info("获取结果：" + GetRedisKV);
        return GetRedisKV;
    }

    @Override
    public String delKV(String key) {
        jedisWrapper = JedisWrapper.singleton();
        String k = KEY_PREFIX + key;
        LOGGER.info("param: key={}", k);
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
        LOGGER.info("param: key={}", k);
        boolean PutRedisKV = jedisWrapper.putNxKV(k, value);
        return PutRedisKV;
    }


    @Override
    public List<RedisEntity> SearchRedisAddress( String ip, int port) {
        if (ip == "" || ip == null || port < 0) {
            return null;
        }
        return redisMapper.SearchRedisAddress(ip, port);
    }

    @Override
    public String AddRedisAddress(String ip, int port) {
        if (ip == "" || ip == null || port < 0) {
            return RedisEnum.NULL.getMsg();
        }
        validateUtils=new ValidateUtils();
        if (validateUtils.isIPAddressByRegex(ip)==false){
            return RedisEnum.ERROR.getMsg();
        }
        if (SearchRedisAddress(ip, port).size() != 0) {
            return RedisEnum.EXIST.getMsg();
        }
        int address = redisMapper.AddRedisAddress(ip, port);
        if (address != 0) {
            return RedisEnum.ADDSUCCESS.getMsg();
        }
        return RedisEnum.ADDFAIL.getMsg();
    }

    @Override
    public String delRedisAddress(String ip, int port) {
        if (ip == "" || ip == null || port < 0) {
            return RedisEnum.NULL.getMsg();
        }
        validateUtils=new ValidateUtils();
        if (validateUtils.isIPAddressByRegex(ip)==false){
            return RedisEnum.ERROR.getMsg();
        }
        if (SearchRedisAddress(ip, port).size() < 1) {
            return RedisEnum.FAIL.getMsg();
        }
        int delredis = redisMapper.delRedisAddress(ip, port);
        if (delredis>0){
            return RedisEnum.DELSUCCESS.getMsg();
        }
        return RedisEnum.DELFALL.getMsg();
    }

}
