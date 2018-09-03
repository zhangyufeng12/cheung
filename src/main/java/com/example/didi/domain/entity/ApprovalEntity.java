package com.example.didi.domain.entity;
/*
*@author  zhangyufeng
*@data 2018/8/10 下午4:21
*/

import java.util.Date;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(Integer approvalType) {
        this.approvalType = approvalType;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Long getCustomId() {
        return customId;
    }

    public void setCustomId(Long customId) {
        this.customId = customId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName == null ? null : reviewerName.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Integer getReferType() {
        return referType;
    }

    public void setReferType(Integer referType) {
        this.referType = referType;
    }

    public Integer getPreApprovalStatus() {
        return preApprovalStatus;
    }

    public void setPreApprovalStatus(Integer preApprovalStatus) {
        this.preApprovalStatus = preApprovalStatus;
    }

    public String getEasyReason() {
        return easyReason;
    }

    public void setEasyReason(String easyReason) {
        this.easyReason = easyReason == null ? null : easyReason.trim();
    }

    public String getIdentifyMsg() {
        return identifyMsg;
    }

    public void setIdentifyMsg(String identifyMsg) {
        this.identifyMsg = identifyMsg == null ? null : identifyMsg.trim();
    }

    public Integer getResign() {
        return resign;
    }

    public void setResign(Integer resign) {
        this.resign = resign;
    }

    public Integer getReviewType() {
        return reviewType;
    }

    public void setReviewType(Integer reviewType) {
        this.reviewType = reviewType;
    }

    public Long getCustomCertificateId() {
        return customCertificateId;
    }

    public void setCustomCertificateId(Long customCertificateId) {
        this.customCertificateId = customCertificateId;
    }

    public String getReasonType() {
        return reasonType;
    }

    public void setReasonType(String reasonType) {
        this.reasonType = reasonType == null ? null : reasonType.trim();
    }

    public Integer getErrorType() {
        return errorType;
    }

    public void setErrorType(Integer errorType) {
        this.errorType = errorType;
    }
}
