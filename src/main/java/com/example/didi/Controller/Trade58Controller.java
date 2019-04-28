package com.example.didi.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.didi.Service.ServiceImpl.TradeService;
import com.example.didi.domain.entity.TradeEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class Trade58Controller {
//    private final static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Trade58Controller.class);
    @Resource
    private TradeService tradeService;

    /**
     * 查詢交易
     * @param orderid
     * @return
     * @throws Exception
     */
    @RequestMapping("/trade/Search")
    @ResponseBody
    public List<TradeEntity> searchtrade( String orderid) throws Exception{
//        String orderid = request.getParameter("orderid");
        log.info("入參為：",orderid);
        return tradeService.SearchTrade(orderid);
    }

    /**
     * 新增交易
     * @param orderid,userid
     * @return
     * @throws Exception
     */
    @RequestMapping("/trade/Ins")
    @ResponseBody
    public JSONObject inserttrade( Long orderid, Long userid) throws Exception{

        log.info("訂單id={}，用戶id={}",orderid,userid);
        JSONObject result = new JSONObject();
        result.put("result",tradeService.AddTrade(Long.valueOf(orderid),Long.valueOf(userid)) );
        log.info("返回結果：" + result + "...orderid為：" + orderid);

        return result;
    }
}
