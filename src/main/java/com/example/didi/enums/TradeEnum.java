package com.example.didi.enums;
/*
*@author  zhangyufeng
*@data 2018/8/11 上午10:02
*/

public enum TradeEnum {

    NULL(0, "参数不能为空"),
    SUCCESS(1,"添加成功"),
    ERROR(2,"参数错误"),
    EXIST(3,"订单id已存在"),
    FAIL(4,"添加失败");

    private int code;
    private String msg;

    TradeEnum( int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static int getTradeEnum(int code){

        for (TradeEnum tradeEnum : TradeEnum.values()) {

            if(tradeEnum.code == code){
                return tradeEnum.code;
            }
        }
        //不存在返回0
        return 0;
    }
}
