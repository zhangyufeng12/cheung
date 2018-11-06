package com.example.didi.tools;
/*
*@author  zhangyufeng
*@data 2018/7/4 下午8:26
*/

import java.util.HashMap;
import java.util.Map;

public class Head {

    private Map<String,String> head = new HashMap<String, String>();
    {
        head.put("Accept-Language", "zh-Hans-US;q=1, en;q=0.9");
        head.put("User-Agent", "daojiajiaoshi/3.0.0.3 (iPhone; iOS 10.3; Scale/2.00)");
        head.put("appVersion", "3003");
        head.put("customType", "2");
        head.put("imei", "7E0AB953-BE82-4356-8869-9736E0859BF0");
        head.put("model", "iPhone Simulator");
        head.put("os", "iOS");
        head.put("osVersion", "10.3");
        head.put("sign", "72cafcb3b22fe314f5892293bf4356dd");
        head.put("timestamp", "1502192030582");
    }

    public Map<String, String> getHead() {
        return head;
    }
    public void setHead( Map<String, String> head ) {
        this.head = head;
    }

}
