package com.example.didi.Service;

public interface RedisService {
    /**
     * redis查询操作
     *
     * @param key
     */
    public String getK( String key );

    /**
     * redis删除操作
     *
     * @param key
     */
    public String delKV( String key );

    /**
     * redis新增操作
     *
     * @param key,value
     */
    public boolean putKV( String key, String value );
}
