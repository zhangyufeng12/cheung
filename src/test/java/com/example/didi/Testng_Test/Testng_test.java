package com.example.didi.Testng_Test;
/*
*@author  zhangyufeng
*@data 2018/7/5 下午2:24
*/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.didi.tools.http.Head;
import com.example.didi.tools.http.HttpClientUtil;
import com.example.didi.tools.http.HttpUtil;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.util.HashMap;


public class Testng_test {

    private HttpUtil ConnectLink = new HttpUtil();
    private Head head = new Head();
    HttpClientUtil httpClientUtil = new HttpClientUtil();
    private Logger log = Logger.getLogger(Testng_test.class);

    //Junit
    @Test
    public void coupon_productserver() {
        head.getHead().put("token", "");

        String result = httpClientUtil.post("https://discovery-dop80b.djtest.cn" +
                        "/coupon/queryProductList", new HashMap<String, String>() {
                    {
                        put("customId", "");
                        put("serviceId", "");
                    }
                }, head.getHead()
        );


        System.out.println("------------+++++++++------------");
        System.out.println(result);
        System.out.println("------------+++++++++------------");

        //加断言
        JSONObject json = JSON.parseObject(result);
        JSONArray datainfo = json.getJSONArray("data");
        JSONObject index = (JSONObject) datainfo.get(0);
        String id = index.getString("serviceId");
        Assert.assertEquals(id, "0");

}

    //testng
    @Test(dataProvider = "test")
    public void test_junit(String mid){
        String result = ConnectLink.doGet("http://localhost:8018/jiedanbao/query?mid="+mid);

        log.info("测试返回结果为："+result);



            /* 格式转换  String -->Jsonarray
                 获取数组中角标为0的元素
                    断言其中user_didi_id=566384711241728*/

        JSONArray json = JSON.parseArray(result);
            if(json.size()>0){

                JSONObject index = json.getJSONObject(0);
                String id = index.getString("user_didi_id");

//            //打印id值
//            System.out.println("--------------");
//            String mid =id.toString();
//            System.out.println(mid);

                Assert.assertEquals(id, "566384711241728");
            }else
                Assert.assertEquals(1,0);

            log.info("over!");

    }

    @Test
    public void ins() {


        String res = httpClientUtil.get("http://localhost:8018/trade/Ins",
                "orderid=1555485534642&userid=1213", head.getHead());

        System.out.println(res);

        JSONObject json = JSON.parseObject(res);
        String r = json.getString("result");
        Assert.assertEquals(r, "订单id已存在");
    }


    @DataProvider
    public  Object[][] test() {
        return new Object[][]{
                {"100100045690"},
                {"1234"}
        };
    }

}
