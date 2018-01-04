package com.example.didi.Service;


import com.example.didi.domain.entity.JiedanbaodelDO;
import com.example.didi.domain.mapper.JiedanbaodelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service("JiedanbaodelService")
public class JiedanbaodelServiceImpl implements JiedanbaodelService{
    @Resource
    private JiedanbaodelMapper jiedanbaodelMapper;

    @Override
    public int delorder(String mid) {
        //分库分表
        int obj = Objects.hashCode(Long.valueOf(mid) % 3);
        System.out.println("suborder:" + obj);
        return jiedanbaodelMapper.delorder(mid,obj);
    }

    public int delpay(String mid) {
        int obj = Objects.hashCode(Long.valueOf(mid) % (3));
        System.out.println("payorder：" + obj);
        return jiedanbaodelMapper.delpay(mid,obj);
    }

    public int delpolicy(String mid) {
        int obj = Objects.hashCode(Long.valueOf(mid) % (3));
        //账务哈希
        //System.out.println(Objects.hash("288395852848988217")%1024);

        System.out.println("policy:" +obj);
        return jiedanbaodelMapper.delpolicy(mid,obj);
    }
    public int deldetail(String mid) {
        int obj = Objects.hashCode(Long.valueOf(mid) % (3));
        System.out.println("paydetail:" +  obj);
        return jiedanbaodelMapper.deldetail(mid, obj);
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
}
