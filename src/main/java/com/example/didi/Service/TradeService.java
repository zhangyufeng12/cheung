package com.example.didi.Service;

import com.example.didi.domain.entity.TradeEntity;

import java.util.List;


public interface TradeService {

    /**
     * 查询交易
     *
     * @param order_id
     */
    List<TradeEntity> SearchTrade( Long order_id );

    /**
     * 增加交易记录
     *
     * @param orderid,userid
     */
    String AddTrade( Long orderid, Long userid );

}
