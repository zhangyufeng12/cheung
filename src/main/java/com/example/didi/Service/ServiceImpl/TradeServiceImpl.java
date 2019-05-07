package com.example.didi.Service.ServiceImpl;

import com.example.didi.Service.TradeService;
import com.example.didi.domain.entity.TradeEntity;
import com.example.didi.domain.mapper.Trade58Mapper;
import com.example.didi.enums.TradeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {
    @Resource
    private Trade58Mapper trade58Mapper;

    @Override
    //查询交易
    public List<TradeEntity> SearchTrade( Long order_id ) {
        //判断orderid正确性
        if (order_id < 0 || order_id == null) {
            return null;
        }
        return trade58Mapper.SearchTrade( order_id );
    }


    //创建交易
    @Override
    public String AddTrade( Long orderid, Long userid ) {

        if (orderid < 0 || userid < 0) {
            return TradeEnum.ERROR.getMsg();
        }
        if (orderid == null) {
            return TradeEnum.NULL.getMsg();
        }

        synchronized (this) {
            //根据orderID查询交易是否存在，存在则不新增
            int Size = SearchTrade( orderid ).size();
            if (Size > 0) {
                return TradeEnum.EXIST.getMsg();
            }
            int trade = trade58Mapper.AddTrade( orderid, userid );

            if (trade != 0) {
                return TradeEnum.SUCCESS.getMsg();
            }
        }
        return TradeEnum.FAIL.getMsg();
    }
}
