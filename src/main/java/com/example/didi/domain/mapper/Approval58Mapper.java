package com.example.didi.domain.mapper;
/*
*@author  zhangyufeng
*@data 2018/8/10 下午4:10
*/

import com.example.didi.domain.entity.ApprovalEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Approval58Mapper {

    List<ApprovalEntity> SearchApproval( @Param("phone") String phone );

    int Approval_status( @Param("custom") Long custom, @Param("approval_status") int approval_status );
}
