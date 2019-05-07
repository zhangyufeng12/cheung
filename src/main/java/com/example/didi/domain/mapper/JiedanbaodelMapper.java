package com.example.didi.domain.mapper;

import com.example.didi.domain.entity.PolicyEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* @zhangyufeng
* 2017.10
* */

@Repository
public interface JiedanbaodelMapper {
    /*
    与数据库交互

     删除
     */
    public int delorder( String mid, int obj );

    public int delpay( String mid, int obj );

    public int delpolicy( String mid, int obj );

    public int deldetail( String mid, int obj );

    public int dellog( String mid );

    public int delgroup( String mid );

    public int delplan( String mid );

    public int delamount( String mid );

    public int delproduct( String mid, int obj );

    /*
        查询
     */

    //json方法编写
//    public List<Map<String,String>> Querypolicy(String mid, int obj);


    //对象方法编写   对象类为policyEntity
    List<PolicyEntity> QueryPolicy( Long mid, int obj );

}
