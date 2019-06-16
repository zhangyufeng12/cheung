package com.example.didi.Service.ServiceImpl;


import com.example.didi.Service.RedisService;
import com.example.didi.domain.entity.RedisEntity;
import com.example.didi.domain.mapper.RedisMapper;
import com.example.didi.enums.RedisEnum;
import com.example.didi.tools.Redis.JedisWrapper;
import com.example.didi.tools.Redis.RedisUtil;
import com.example.didi.tools.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.Resource;
import java.util.List;

@Service("RedisService")
public class RedisServiceImpl implements RedisService {
    private final static String KEY_PREFIX = "selectRedisKey";
    private final static Logger LOGGER = LoggerFactory.getLogger( RedisServiceImpl.class );
    ValidateUtils validateUtils;
    JedisWrapper jedisWrapper;
    RedisUtil redisUtil;
    @Resource
    private RedisMapper redisMapper;

    @Override
    public String getK( String ip, int port, String key ) {
        if (ip == "" || ip == null || port < 0) {
            return RedisEnum.NULL.getMsg();
        }
        //ip格式校验
        validateUtils = new ValidateUtils();
        if (validateUtils.isIPAddressByRegex( ip ) == false) {
            return RedisEnum.ERROR.getMsg();
        }
        try {
            redisUtil = new RedisUtil();
            RedisUtil.init( ip, port );
            jedisWrapper = JedisWrapper.singleton();
            String k = KEY_PREFIX + key;
            LOGGER.info( "RedisServiceImpl.getK():param:{ key={},ip={},port={} }", k, ip, port );
            String GetRedisKV = jedisWrapper.getKV( k );
            return GetRedisKV;
        } catch (JedisConnectionException jedisConnectionException) {
            LOGGER.error( "redis连接错误" );
            return RedisEnum.NULL.getMsg();
        } catch (Exception e) {
            LOGGER.error( "错误" );
            return "错误";
        }
    }

    @Override
    public String delKV( String ip, int port, String key ) {
        if (ip == "" || ip == null || port < 0) {
            return RedisEnum.NULL.getMsg();
        }
        //ip格式校验
        validateUtils = new ValidateUtils();
        if (validateUtils.isIPAddressByRegex( ip ) == false) {
            return RedisEnum.ERROR.getMsg();
        }
        try {
            redisUtil = new RedisUtil();
            RedisUtil.init( ip, port );
            jedisWrapper = JedisWrapper.singleton();
            String k = KEY_PREFIX + key;
            LOGGER.info( "RedisServiceImpl.delKV():param:{ key={},ip={},port={} }", k, ip, port );
            if (jedisWrapper.getKV( k ) != null) {
                jedisWrapper.removeKV( k );
                return RedisEnum.DELSUCCESS.getMsg() + "..查询结果" + jedisWrapper.getKV( k );
            }
            return RedisEnum.NON.getMsg();
        } catch (JedisConnectionException jedisConnectionException) {
            LOGGER.error( "redis连接错误" );
            return RedisEnum.NULL.getMsg();
        } catch (Exception e) {
            LOGGER.error( "错误" );
            return "错误";
        }
    }

    @Override
    public boolean putKV( String ip, int port, String key, String value ) {
        try {
            redisUtil = new RedisUtil();
            RedisUtil.init( ip, port );
            jedisWrapper = JedisWrapper.singleton();
            String k = KEY_PREFIX + key;
            LOGGER.info( "RedisServiceImpl.putKV():param:{ key={},ip={},port={} }", k, ip, port );
            boolean PutRedisKV = jedisWrapper.putNxKV( k, value );
            return PutRedisKV;
        } catch (JedisConnectionException jedisConnectionException) {
            LOGGER.error( "redis连接错误" );
            return false;
        } catch (Exception e) {
            LOGGER.error( "错误" );
            return false;
        }

    }


    @Override
    public List<RedisEntity> SearchRedisAddress( String ip, int port ) {
        if (ip == "" || ip == null || port < 0) {
            return null;
        }
        return redisMapper.SearchRedisAddress( ip, port );
    }

    @Override
    public String AddRedisAddress( String ip, int port, String explain ) {
        //基础参数校验
        if (ip == "" || ip == null || port < 0) {
            return RedisEnum.NULL.getMsg();
        }
        //ip格式校验
        validateUtils = new ValidateUtils();
        if (validateUtils.isIPAddressByRegex( ip ) == false) {
            return RedisEnum.ERROR.getMsg();
        }
        //判断新增地址是否存在
        if (SearchRedisAddress( ip, port ).size() != 0) {
            return RedisEnum.EXIST.getMsg();
        }
        int address = redisMapper.AddRedisAddress( ip, port, explain );
        if (address != 0) {
            return RedisEnum.ADDSUCCESS.getMsg();
        }
        return RedisEnum.ADDFAIL.getMsg();
    }

    @Override
    public String delRedisAddress( String ip, int port ) {
        if (ip == "" || ip == null || port < 0) {
            return RedisEnum.NULL.getMsg();
        }
        validateUtils = new ValidateUtils();
        if (validateUtils.isIPAddressByRegex( ip ) == false) {
            return RedisEnum.ERROR.getMsg();
        }
        if (SearchRedisAddress( ip, port ).size() < 1) {
            return RedisEnum.FAIL.getMsg();
        }
        int delredis = redisMapper.delRedisAddress( ip, port );
        if (delredis > 0) {
            return RedisEnum.DELSUCCESS.getMsg();
        }
        return RedisEnum.DELFALL.getMsg();
    }

    @Override
    public List<RedisEntity> getRedisConnection() {
        return redisMapper.queryAllRedisConnection();
    }


}
