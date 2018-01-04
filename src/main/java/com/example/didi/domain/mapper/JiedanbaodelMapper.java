package com.example.didi.domain.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface JiedanbaodelMapper {
    //与数据库交互
    public int delorder(String mid,int obj);
    public int delpay(String mid,int obj);
    public int delpolicy(String mid,int obj);
    public int deldetail(String mid,int obj);
    public int dellog(String mid);
    public int delgroup(String mid);
    public int delplan(String mid);
    public int delamount(String mid);
}
