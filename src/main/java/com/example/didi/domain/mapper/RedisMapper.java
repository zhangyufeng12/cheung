package com.example.didi.domain.mapper;

import com.example.didi.domain.entity.RedisEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RedisMapper {

    public List<RedisEntity> SearchRedisAddress( @Param("ip") String ip, @Param("port") int port );

    public int AddRedisAddress( @Param("ip") String ip, @Param("port") int port );

    public int delRedisAddress( @Param("ip") String ip, @Param("port") int port );

}
