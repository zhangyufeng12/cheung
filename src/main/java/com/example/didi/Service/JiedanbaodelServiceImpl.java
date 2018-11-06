package com.example.didi.Service;

/*
* @zhangyufeng
* 2017.10
* */

import com.example.didi.Service.ServiceImpl.JiedanbaodelService;
import com.example.didi.domain.entity.PolicyEntity;
import com.example.didi.domain.mapper.JiedanbaodelMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service("JiedanbaodelService")
public class JiedanbaodelServiceImpl implements JiedanbaodelService {
    private Logger log= Logger.getLogger(JiedanbaodelServiceImpl.class);
    @Resource
    private JiedanbaodelMapper jiedanbaodelMapper;
    /*
        根据mid删除数据
     */

    @Override
    public int delorder(String mid) {
        //分库分表
        int obj = Objects.hashCode(Long.valueOf(mid) % 3);
//        System.out.println("suborder:" + obj);
        return jiedanbaodelMapper.delorder(mid,obj);
    }

    public int delpay(String mid) {
        int obj = Objects.hashCode(Long.valueOf(mid) % (3));
//        System.out.println("payorder：" + obj);
        return jiedanbaodelMapper.delpay(mid,obj);
    }

    public int delpolicy(String mid) {
        int obj = Objects.hashCode(Long.valueOf(mid) % (3));
        //账务哈希
        //System.out.println(Objects.hash("288395852848988217")%1024);

//        System.out.println("policy:" +obj);
        return jiedanbaodelMapper.delpolicy(mid,obj);
    }
    public int deldetail(String mid) {
        int obj = Objects.hashCode(Long.valueOf(mid) % (3));
//        System.out.println("paydetail:" +  obj);
        return jiedanbaodelMapper.deldetail(mid, obj);
    }
    public int delproduct(String mid){
        int obj = Objects.hashCode(Long.valueOf(mid) % (3));
        return jiedanbaodelMapper.delproduct(mid,obj);
    }



    public int dellog(String mid) {
        return jiedanbaodelMapper.dellog(mid);
    }
    public int delgroup(String mid) {
        return jiedanbaodelMapper.delgroup(mid);
    }
    public int delplan(String mid) {
        return jiedanbaodelMapper.delplan(mid);
    }
    public int delamount(String mid){return jiedanbaodelMapper.delamount(mid);}


    /*
        根据mid查询字段
     */

    /*
        对象方法
        2018.1.24
     */

    @Override
    public List<PolicyEntity> QueryPolicy(String mid) {

        //通过接口获取phone值
//        String result = new HttpUtil().doGet("");
//        JSONArray json = JSON.parseArray(result);
//        JSONObject index = json.getJSONObject(0);
//        String phone = index.getString("phone");
//
        int obj = Objects.hashCode(Long.valueOf(mid) % 3);
        log.info("分库分表信息为库："+ obj);
        return jiedanbaodelMapper.QueryPolicy(mid,obj);
    }



        /*
         json方法编写
         2018.1.10
        */
//
//        public List<Map<String,String>> Querypolicy(String mid){
//        int obj = Objects.hashCode(Long.valueOf(mid) % 3);
//        return jiedanbaodelMapper.Querypolicy(mid,obj);
//    }



}
