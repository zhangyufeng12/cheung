package com.example.didi.Service;
/*
* @zhangyufeng
* 2017.10
* */

import com.example.didi.domain.entity.PolicyEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface JiedanbaodelService {
    /*
        删除
     */
    public int delorder(String mid);
    public int delpolicy(String mid);
    public int deldetail(String mid);
    public int dellog(String mid);
    public int delgroup(String mid);
    public int delplan(String mid);
    public int delamount(String mid);
    public int delpay(String mid);
    public int delproduct(String mid);

    /*
        查询
     */

    List<PolicyEntity> QueryPolicy(String mid);  //对象方法


//       public List<Map<String,String>> Querypolicy(String mid);  //json方法编写

}
