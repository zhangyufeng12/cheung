package com.example.didi.tools.Redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

public class RedisUtil {

    private static final Log logger = LogFactory.getLog( RedisUtil.class );


    //服务器IP地址
    private static String ADDR = RedisConfig.redis_ip1;
    //端口
    private static int PORT = Integer.valueOf( RedisConfig.redis_port1 );
    //密码
    private static String AUTH = "123456";
    //连接实例的最大连接数
    private static int MAX_ACTIVE = 1024;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private static int MAX_WAIT = 10000;
    //连接超时的时间　　
    private static int TIMEOUT = 10000;

    private static boolean TEST_ON_BORROW = true;
    public static JedisPool jedisPool = null;

    /**
     *  初始化资源池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal( MAX_ACTIVE );
            config.setMaxIdle( MAX_IDLE );
            config.setMaxWaitMillis( MAX_WAIT );
            config.setTestOnBorrow( TEST_ON_BORROW );
            jedisPool = new JedisPool( config, ADDR, PORT, TIMEOUT, AUTH );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 获取资源
     *
     * @return
     * @throws Exception
     */
    public static synchronized Jedis getJedis() throws Exception {
        try {
            if (jedisPool != null) {
                Jedis e = jedisPool.getResource();
                return e;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error( e );
            return null;
        }
    }

    /**
     * 返还到连接池
     *
     * @param jedisSentinelPool
     * @param redis
     */
    @SuppressWarnings("deprecation")
    public static void returnResource( JedisSentinelPool jedisSentinelPool, Jedis redis ) {
        if (redis != null) {
            jedisSentinelPool.returnResource( redis );
        }
    }
}
