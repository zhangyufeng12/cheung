package com.example.didi.domain.mapper;
/*
*@author  zhangyufeng
*@data 2018/8/10 下午4:10
*/

import com.example.didi.domain.entity.ApprovalEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Approval58Mapper {

    List<ApprovalEntity> SearchApproval( String phone );


    int Approval_status( Long custom , int approval_status);

}
