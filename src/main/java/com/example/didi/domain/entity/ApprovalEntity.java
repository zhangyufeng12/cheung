package com.example.didi.domain.entity;
/*
*@author  zhangyufeng
*@data 2018/8/10 下午4:21
*/

import lombok.Data;

import java.util.Date;

@Data
public class ApprovalEntity {
    private Long id;

    private Integer approvalType;

    private Integer approvalStatus;

    private Long customId;

    private Long productId;

    private Long reviewerId;

    private String reviewerName;

    private String reason;

    private Date createDate;

    private Date updateTime;

    private Long orgId;

    private Integer referType;

    private Integer preApprovalStatus;

    private String easyReason;

    private String identifyMsg;

    private Integer resign;

    private Integer reviewType;

    private Long customCertificateId;

    private String reasonType;

    private Integer errorType;

    private Integer faceCertifyStatus;

    private String intoApprovalReason;

}
