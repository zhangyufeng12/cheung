package com.example.didi.domain.entity;

public class PolicyEntity {
    String insurant_name;      //被保人
    String product_name;       //产品名称
    String user_mobile;        //手机号
    String user_didi_id;        //滴滴id
    int status;                //保单状态
    String pay_order_id;       //支付订单id
    String inscompany_name;    //保险公司名称
    int max_insured_amount;    //最大保额
    int insured_amount;        //保额
    int premium;               //保费
    String user_mid;

    public String getInsurant_name() {
        return insurant_name;
    }

    public void setInsurant_name(String insurant_name) {
        this.insurant_name = insurant_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getUser_didi_id() {
        return user_didi_id;
    }

    public void setUser_didi_id(String user_didi_id) {
        this.user_didi_id = user_didi_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPay_order_id() {
        return pay_order_id;
    }

    public void setPay_order_id(String pay_order_id) {
        this.pay_order_id = pay_order_id;
    }

    public String getInscompany_name() {
        return inscompany_name;
    }

    public void setInscompany_name(String inscompany_name) {
        this.inscompany_name = inscompany_name;
    }

    public int getMax_insured_amount() {
        return max_insured_amount;
    }

    public void setMax_insured_amount(int max_insured_amount) {
        this.max_insured_amount = max_insured_amount;
    }

    public int getInsured_amount() {
        return insured_amount;
    }

    public void setInsured_amount(int insured_amount) {
        this.insured_amount = insured_amount;
    }

    public int getPremium() {
        return premium;
    }

    public void setPremium(int premium) {
        this.premium = premium;
    }

    public String getUser_mid() {
        return user_mid;
    }

    public void setUser_mid(String user_mid) {
        this.user_mid = user_mid;
    }
}
