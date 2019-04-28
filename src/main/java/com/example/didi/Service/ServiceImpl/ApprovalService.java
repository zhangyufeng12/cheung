package com.example.didi.Service.ServiceImpl;
/*
*@author  zhangyufeng
*@data 2018/8/10 下午4:35
*/

import com.example.didi.domain.entity.ApprovalEntity;

import java.util.List;

public interface ApprovalService {

    //查询
    List<ApprovalEntity> SearchApproval( String phone );

    //修改
    String Approval_status(String phone ,int approval_status);


}
