package com.example.didi.domain.mapper;

import com.example.didi.domain.entity.TradeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Trade58Mapper {
    //查询交易
    public List<TradeEntity> SearchTrade( Long order_id );

    //新增交易
    public int AddTrade( @Param("orderid") Long orderid, @Param("userid") Long userid );

}
