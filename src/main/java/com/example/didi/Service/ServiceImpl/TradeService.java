package com.example.didi.Service.ServiceImpl;

import com.example.didi.domain.entity.TradeEntity;

import java.util.List;


public interface TradeService {

    List<TradeEntity> SearchTrade( String order_id );

    String AddTrade( Long orderid, Long userid );

}
