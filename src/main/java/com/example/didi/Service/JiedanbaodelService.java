package com.example.didi.Service;
/*
* @zhangyufeng
* 2017.10
* */

import com.example.didi.domain.entity.PolicyEntity;

import java.util.List;

public interface JiedanbaodelService {
    /**
     * 删除订单
     *
     * @param mid
     */
    public int delorder( String mid );

    /**
     * 删除保单
     *
     * @param mid
     */
    public int delpolicy( String mid );

    /**
     * 删除明细
     *
     * @param mid
     */
    public int deldetail( String mid );

    /**
     * 删除记录
     *
     * @param mid
     */
    public int dellog( String mid );

    /**
     * 删除组信息
     *
     * @param mid
     */
    public int delgroup( String mid );

    /**
     * 删除加入计划
     *
     * @param mid
     */
    public int delplan( String mid );

    /**
     * 删除金额明细记录
     *
     * @param mid
     */
    public int delamount( String mid );

    /**
     * 删除支付信息
     *
     * @param mid
     */
    public int delpay( String mid );

    /**
     * 删除产品信息
     *
     * @param mid
     */
    public int delproduct( String mid );


    /**
     * 查询已加入保单
     *
     * @param mid
     */
    List<PolicyEntity> QueryPolicy( Long mid );  //对象方法


//       public List<Map<String,String>> Querypolicy(String mid);  //json方法编写

}
