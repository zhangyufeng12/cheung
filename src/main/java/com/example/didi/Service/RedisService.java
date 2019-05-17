package com.example.didi.Service;

import com.example.didi.domain.entity.RedisEntity;

import java.util.List;

public interface RedisService {
    /**
     * redis查询操作
     * @param key
     */
    public String getK(String key);

    /**
     * redis删除操作
     * @param key
     */
    public String delKV(String key);

    /**
     * redis新增操作
     * @param key,value
     */
    public boolean putKV(String key,String value);

    /**
     * redis查询地址
     * @param ip,port
     */
    public List<RedisEntity> SearchRedisAddress( String ip, int port);

    /**
     * redis新增地址
     * @param ip,port
     */
    public String AddRedisAddress(String ip,int port);

    /**
     * redis删除地址
     * @param ip,port
     */
    public String delRedisAddress(String ip,int port);
}
