package com.example.didi;
/*
*@author  zhangyufeng
*@data 2018/7/5 下午2:24
*/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.testng.Assert;

import java.util.HashMap;


public class Junit_test {

    private HttpUtil ConnectLink = new HttpUtil();
    private Head head = new Head();



    @Test
    public void coupon_productserver() {
        String result = ConnectLink.doPost("https://discovery-dop80b.djtest.cn" +
                    "/coupon/queryProductList", new HashMap<String, String>() {
            {
                put("customId", "");
                put("serviceId", "");
            }
        }
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



    @Test()
    public void test_junit(){
        String result = ConnectLink.doGet("http://localhost:8018/jiedanbao/query?mid=100100045690");

        System.out.println(result);

        try {

            /* 格式转换  String -->Jsonarray
                 获取角标为0的数组
                    断言其中user_didi_id=566384711241728*/

            JSONArray json = JSON.parseArray(result);
            JSONObject index = json.getJSONObject(0);
            String id = index.getString("user_didi_id");

//            //打印id值
//            System.out.println("--------------");
//            String mid =id.toString();
//            System.out.println(mid);

            Assert.assertEquals(id, "566384711241728");



        }catch (AssertionError e){
            e.printStackTrace();
        }finally {
            System.out.println("over!");
        }

    }



}
