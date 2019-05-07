package com.example.didi.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.didi.Service.RedisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class RedisController {
    @Resource
    private RedisService redisService;


    /**
     * redis查询操作
     *
     * @param key
     * @return
     * @throws Exception
     */
    @RequestMapping("/redis/getkv")
    @ResponseBody
    public JSONObject GetKV( String key ) throws Exception {
        JSONObject result = new JSONObject();
        result.put( "result", redisService.getK( key ) );
        return result;
    }

    /**
     * redis删除操作
     *
     * @param key
     * @return
     * @throws Exception
     */
    @RequestMapping("/redis/delkv")
    @ResponseBody
    public JSONObject DelKV( String key ) throws Exception {
        JSONObject result = new JSONObject();
        result.put( "result", redisService.delKV( key ) );
        return result;
    }

    /**
     * redis新增操作
     *
     * @param key,value
     */
    @RequestMapping("/redis/putkv")
    @ResponseBody
    public JSONObject PutKV( String key, String value ) throws Exception {
        JSONObject result = new JSONObject();
        result.put( "result", redisService.putKV( key, value ) );
        return result;
    }

}
