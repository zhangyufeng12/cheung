package com.example.didi.tools.Redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.*;

public class JedisWrapper {
    private final static Logger logger = LoggerFactory.getLogger( JedisWrapper.class );

    private static JedisWrapper jedisWrapper = null;


    /**
     * 单例模式获取实例
     *
     * @return
     */
    public static JedisWrapper singleton() {
        if (jedisWrapper == null) {
            synchronized (JedisWrapper.class) {
                if (jedisWrapper == null) {
                    jedisWrapper = new JedisWrapper();
                }
            }
        }
        return jedisWrapper;
    }

    /**
     * 给定KEY的对象是否已经存在
     *
     * @param key
     * @return
     */
    public boolean exists( String key ) {
        logger.debug( ">>>> exists ---- is exists key ---- " + key );
        boolean re = true;
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            re = jedis.exists( key );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        logger.debug( "<<<< exists ---- is exists key ---- result: " + re );
        return re;
    }

    public void batchDel( String key ) {
        Jedis jedis;
        try {
            jedis = RedisUtil.getJedis();
            Set<String> set = jedis.keys( key + "*" );
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                String keyStr = it.next();
                jedis.del( keyStr );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询hash的某个字段是否存在
     *
     * @param key
     * @param field
     * @return
     */
    public boolean hexists( String key, String field ) {
        logger.debug( ">>>> exists ---- is exists key ---- " + key );
        boolean re = true;
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            re = jedis.hexists( key, field );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        logger.debug( "<<<< exists ---- is exists key ---- result: " + re );
        return re;

    }

    /**
     * redis保存key-value数据
     *
     * @param key
     * @param value
     * @return
     */
    public boolean putKV( String key, String value ) {
        logger.debug( ">>>> putKV ----- key : {}----value : {}", key, value );
        boolean re = true;
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            jedis.set( key, value );
            re = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        logger.debug( "<<<< putKV ----- key : {}----result : {}", key, re );
        return re;
    }

    /**
     * redis保存key-value数据
     *
     * @param key
     * @param value
     * @param seconds 大于0表示为给定 key 设置生存时间，小于等于0表示未设置生存时间
     * @return
     */
    public boolean putKV( String key, String value, int seconds ) {
        logger.debug( ">>>> putKV ---- life time: " + seconds + " -------key : {} ---", key );
        boolean re = true;
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            jedis.set( key, value );
            if (seconds > 0) {
                jedis.expire( key, seconds );
            }
            re = true;
        } catch (Exception e) {
            logger.error( "jedis putKV exception", e );
        } finally {
            jedis.close();
        }
        logger.debug( "<<<< putKV ----- key : {}----result : {}", key, re );
        return re;
    }

    /**
     * redis 将 key 的值设为 value ，当且仅当 key 不存在。若给定的 key 已经存在，则不做任何动作。  新增
     *
     * @param key
     * @param value
     * @return
     */
    public boolean putNxKV( String key, String value ) {
        logger.debug( ">>>> putNxKV ----- key : " + key );
        boolean re = true;
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            long set = jedis.setnx( key, value );
            re = set > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        logger.debug( "<<<< putNxKV ----- key : {}----result : {}", key, re );
        return re;
    }

    /**
     * 返回redis 中 key 所关联的字符串值。
     *
     * @param key
     * @return
     */
    public String getKV( String key ) {
        logger.debug( ">>>> getKV ----- key : " + key );
        String re = null;
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            re = jedis.get( key );
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
        } finally {
            //返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
        logger.debug( "<<<< getKV ----- key : {}----result : {}", key, "success!" );
        return re;
    }

    /**
     * 删除redis中给定的 key 。不存在的 key 会被忽略。
     *
     * @param key
     * @return 删除条数
     */
    public String removeKV( String key ) {
        logger.debug( ">>>> removeKV ----- key : " + key );
        String re = null;
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            long s = jedis.del( key );
            re = s + "";
        } catch (Exception e) {
            logger.error( "jedis removeKV exception", e );
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
        logger.debug( "<<<< removeKV ----- key : {}----result : {}", key, re );
        return re;
    }

    /**
     * 删除redis中给定的一个或者多个key 。不存在的 key 会被忽略。
     *
     * @param keys
     * @return 删除条数
     */
    public String removeKV( String... keys ) {
        logger.debug( ">>>> removeKV ----- keys : " + keys );
        String re = null;
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            long s = jedis.del( keys );
            re = s + "";
        } catch (Exception e) {
            logger.error( "jedis removeKV array exception", e );
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
        logger.debug( "<<<< removeKV ----- keys : {}----result : {}", keys, re );
        return re;
    }


    /**
     * 将一个或多个值 value 插入到redis列表 key 的表头
     *
     * @param key
     * @param values
     * @return
     */
    public boolean putList( String key, String... values ) {
        logger.debug( ">>>> putList ----- key : {} ---- values : {}", key, "" );
        boolean re = true;
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            for (String data : values) {
                jedis.lpush( key, data );
            }
            re = true;
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
        logger.debug( "<<<< putList ----- key : {}----result : {}", key, re );
        return re;
    }

    /**
     * 返回redis列表 key 中的第一个元素。不移除。
     *
     * @param key
     * @return
     */
    public String getList( String key ) {
        logger.debug( ">>>> getList ----- get first value ----- key : {} ", key );
        String re = null;
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            String value = jedis.lindex( key, 0 );
            re = value;
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
        logger.debug( "<<<< getList ----- get first value ----- key : {}----result : {}", key, re );
        return re;
    }

    /**
     * 移除并返回redis列表 key 中的第一个元素
     *
     * @param key
     * @return
     */
    public String popList( String key ) {
        logger.debug( ">>>> popList ----- pop first value ----- key : {} ", key );
        String re = null;
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            String value = jedis.lpop( key );
            re = value;
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
        logger.debug( "<<<< popList ----- pop first value ----- key : {}----result : {}", key, re );
        return re;
    }

    /**
     * 将redis哈希表 key 中的域 field 的值设为 value 。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public boolean putMap( String key, String field, String value ) {
        logger.debug( ">>>> putMap ----- put value to key's field ----- key : " + key + " --- field : " + field + " --- value : " + value );
        boolean re = true;
        Jedis jedis = null;
        Long newCount = (long) 0;
        try {
            jedis = RedisUtil.getJedis();
            newCount = jedis.hset( key, field, value );
            re = newCount == 0 ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
        logger.debug( "<<<< putMap ----- put value to key's field ----- key : {}----result : {}", key, re );
        return re;
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。 会覆盖哈希表中已存在的域。
     *
     * @param key
     * @param map
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean putMap( String key, Map map ) {
        logger.debug( ">>>> putMap ----- put whole map ----- key : {} --- value : {}", key, "" );
        Jedis jedis = null;
        boolean re = true;
        try {
            String res;
            jedis = RedisUtil.getJedis();
            res = jedis.hmset( key, map );
            re = "OK".equalsIgnoreCase( res ) ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
        logger.debug( "<<<< putMap ----- put whole map ----- key : {}----result : {}", key, re );
        return re;
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。 会覆盖哈希表中已存在的域。
     *
     * @param key
     * @param map
     * @param seconds 大于0表示为给定 key 设置生存时间，小于等于0表示未设置生存时间
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public boolean putMap( String key, Map map, int seconds ) {
        logger.debug( ">>>> putMap ----- put whole map with lifetime ----- key : {} --- value : {} --- lifetime : " + seconds, key, "" );
        Jedis jedis = null;
        boolean re = true;
        try {
            String res = null;
            jedis = RedisUtil.getJedis();
            res = jedis.hmset( key, map );
            if (seconds > 0) {
                jedis.expire( key, seconds );
            }
            re = res == "OK" ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
        logger.debug( "<<<< putMap ----- put whole map ----- key : {}----result : {}", key, re );
        return re;
    }

    /**
     * 获取redis哈希表key中的域 field 的值。
     *
     * @param key
     * @param field
     * @return
     */
    public String getMap( String key, String field ) {
        logger.debug( ">>>> getMap ----- get key's field value ----- key : {} --- field : {}", key, field );
        Jedis jedis = null;
        String re = null;
        try {
            jedis = RedisUtil.getJedis();
            re = jedis.hget( key, field );
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
        logger.debug( "<<<< getMap ----- get key's field value ----- key : {}----result : {}", key, "success!" );
        return re;
    }

    /**
     * 获取redis哈希中多个field的value
     *
     * @param key
     * @param fields
     * @return
     */
    public List<String> getMMap( String key, String... fields ) {
        logger.debug( ">>>> getMMap ----- get key's many field values ----- key : {} --- fields : {}", key, "" );
        List<String> values = new ArrayList<String>();
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            values = jedis.hmget( key, fields );
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
        logger.debug( "<<<< getMMap ----- get key's many field values ----- key : {} --- result : {}", key, "success!" );
        return values;
    }

    /**
     * 获取redis中的整个哈希
     *
     * @param key
     * @return
     */
    public Map<String, String> getMapAll( String key ) {
        logger.debug( ">>>> getMapAll ----- get whole map ----- key : {} ", key );
        Jedis jedis = null;
        Map<String, String> map = null;
        try {
            jedis = RedisUtil.getJedis();
            map = jedis.hgetAll( key );
            logger.debug( "<<<< getMapAll ----- get whole map ----- key : {} --- result : {}", key, "success!" );
            return map.size() == 0 ? null : map;
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
        return null;
    }

    /**
     * 为哈希表 key 中的域 field 的值加上增量 value 。
     * 增量也可以为负数，相当于对给定域进行减法操作。
     * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
     * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long incredMap( String key, String field, int value ) {
        logger.debug( ">>>> incredMap ----- increase map field's value ----- key : " + key + " --- field : " + field + " --- value : " + value );
        Jedis jedis = null;
        Long newCount = Long.parseLong( "-1" );
        try {
            jedis = RedisUtil.getJedis();
            newCount = jedis.hincrBy( key, field, value );
            logger.debug( "<<<< incredMap ----- increase map field's value ----- key : {} --- field : {} --- result : " + newCount, key, field );
            return newCount;
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
            return null;
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }

    }

    /**
     * 删除redis中key的field。
     *
     * @param key
     * @param field
     * @return
     */
    public boolean removeMap( String key, String field ) {
        logger.debug( ">>>> removeMap ----- remove map's field ----- key : {} --- field : {}", key, field );
        Jedis jedis = null;
        Long newCount = (long) 0;
        boolean re = true;
        try {
            jedis = RedisUtil.getJedis();
            newCount = jedis.hdel( key, field );
            re = newCount == 0 ? false : true;
            logger.debug( "<<<< removeMap ----- remove map's field ----- key : {} --- field : {} --- result : " + re, key, field );
            return re;
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
            return false;
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
    }

    /**
     * 给redis中的多个哈希设置生存时间
     *
     * @param keys
     * @param fields
     * @param value
     * @param seconds
     * @return
     */
    public boolean putMapExiperse( String[] keys, String[] fields, String[] value, int[] seconds ) {
        Jedis jedis = null;

        try {
            jedis = RedisUtil.getJedis();
            Pipeline plin = jedis.pipelined();
            for (int i = 0; i < keys.length; i++) {
                plin.hset( keys[i], fields[i], value[i] );
                plin.expire( keys[i], seconds[i] );
            }
            List<Object> r = plin.syncAndReturnAll();
            return r == null ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
            return false;
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
    }

    /**
     * 获取有序集合中所有的数据
     *
     * @param key
     * @return
     */
    public Set<String> getSortSet( String key ) {
        logger.debug( ">>>> getSortSet ----- get sort set ----- key : {}", key );
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            Set<String> res = jedis.zrangeByScore( key, 0, System.currentTimeMillis() );
            return res.size() == 0 ? null : res;
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
            return null;
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
    }


    /**
     * 设置key的生存时间
     *
     * @param key
     * @param seconds
     * @return
     */
    public boolean setExiperse( String key, int seconds ) {
        logger.debug( ">>>> setExiperse ----- set key's lifetime ----- key : {}", key );
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();
            long r = jedis.expire( key, seconds );
            if (r > 0) {
                logger.debug( "<<<< setExiperse---- set key's lifetime ----- key : {} --- seconds : {}", key, seconds );
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
//        	//释放redis对象
//        	JedisSentinelUtil.jedisSentinelPool.returnBrokenResource(jedis);
            return false;
        } finally {
//        	//返还到连接池
//        	JedisSentinelUtil.returnResource(JedisSentinelUtil.jedisSentinelPool, jedis);
            jedis.close();
        }
    }

}
