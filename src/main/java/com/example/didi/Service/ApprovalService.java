package com.example.didi.Service;
/*
*@author  zhangyufeng
*@data 2018/8/10 下午4:35
*/

import com.example.didi.domain.entity.ApprovalEntity;

import java.util.List;

public interface ApprovalService {

    /**
     * 查询认证信息
     *
     * @param phone
     */
    List<ApprovalEntity> SearchApproval( String phone );

    /**
     * 修改审核状态
     *
     * @param phone,approval_status
     */
    String Approval_status( String phone, int approval_status );


}
