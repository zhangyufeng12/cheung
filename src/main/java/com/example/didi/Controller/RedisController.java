package com.example.didi.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.didi.Service.RedisService;
import com.example.didi.domain.entity.RedisEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/redis")
public class RedisController {

    private final static Logger LOGGER = LoggerFactory.getLogger( RedisController.class );
    @Resource
    private RedisService redisService;


    /**
     * redis查询操作
     *
     * @param key
     * @return
     * @throws Exception
     */
    @RequestMapping("/getkv")
    @ResponseBody
    public JSONObject GetKV( String key ) throws Exception {
        JSONObject result = new JSONObject();
        result.put( "result", redisService.getK( key ) );
        LOGGER.info( "result：" + result );
        return result;
    }

    /**
     * redis删除操作
     *
     * @param key
     * @return
     * @throws Exception
     */
    @RequestMapping("/delkv")
    @ResponseBody
    public JSONObject DelKV( String key ) throws Exception {
        JSONObject result = new JSONObject();
        result.put( "result", redisService.delKV( key ) );
        LOGGER.info( "result：" + result );
        return result;
    }

    /**
     * redis新增操作
     *
     * @param key,value
     */
    @RequestMapping("/putkv")
    @ResponseBody
    public JSONObject PutKV( String key, String value ) throws Exception {
        JSONObject result = new JSONObject();
        result.put( "result", redisService.putKV( key, value ) );
        LOGGER.info( "result：" + result );
        return result;
    }


    /**
     * redis查询地址
     * @param ip,port
     */
    @RequestMapping("/address/search")
    @ResponseBody
    public List<RedisEntity> SearchRedisAddress( String ip, int port) throws Exception {
        LOGGER.info("Param: ip={},port={}", ip, port);
        List<RedisEntity> result= redisService.SearchRedisAddress(ip, port);
        LOGGER.info("result：" + result);
        return result;
    }

    /**
     * redis新增地址
     *
     * @param ip,port
     */
    @RequestMapping("/address/add")
    @ResponseBody
    public JSONObject AddRedisAddress( String ip, int port ) throws Exception {
        LOGGER.info( "Param: ip={},port={}", ip, port );
        JSONObject result = new JSONObject();
        result.put( "result", redisService.AddRedisAddress( ip, port ) );
        LOGGER.info( "result：" + result );
        return result;
    }

    /**
     * redis删除地址
     *
     * @param ip,port
     */
    @RequestMapping("/address/del")
    @ResponseBody
    public JSONObject delRedisAddress( String ip, int port ) throws Exception {
        LOGGER.info( "Param: ip={},port={}", ip, port );
        JSONObject result = new JSONObject();
        result.put( "result", redisService.delRedisAddress( ip, port ) );
        LOGGER.info( "result：" + result );
        return result;
    }

}
