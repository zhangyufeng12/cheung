package com.example.didi.enums;
/*
 *@author  zhangyufeng
 *@data 2019/5/13 上午15:02
 */

public enum RedisEnum {

    NULL(0, "参数不能为空"),
    ADDSUCCESS(1, "添加成功"),
    DELSUCCESS(2, "删除成功"),
    FAIL(3, "对应参数不存在"),
    ADDFAIL(4, "添加失败"),
    NON(5, "该key在redis中已不存在"),
    EXIST(6, "对应参数已存在"),
    DELFALL(7, "添加失败"),
    ERROR(8,"ip格式错误");


    private int code;
    private String msg;

    RedisEnum( int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static int getApprovalStatusEnum(int code) {

        for (RedisEnum approvalStatus : RedisEnum.values()) {

            if (approvalStatus.code == code) {
                return approvalStatus.code;
            }
        }
        //不存在返回0
        return 0;
    }
}
