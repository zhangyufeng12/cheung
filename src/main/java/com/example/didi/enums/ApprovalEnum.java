package com.example.didi.enums;
/*
*@author  zhangyufeng
*@data 2018/8/11 上午10:02
*/

public enum  ApprovalEnum {

    NULL(0, "未填写"),
    WAIT(1,"待审核"),
    SUCCESS(2,"已审核"),
    LOSE(3,"审核失败"),
    FAIL(4,"审核异常"),
    NON(5,"未查询到需要修改的数据"),
    EXCE(6,"状态不存在");

    private int code;
    private String msg;

    ApprovalEnum( int code, String msg ) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static int getApprovalStatusEnum( int code ) {

        for (ApprovalEnum approvalStatus : ApprovalEnum.values()) {

            if (approvalStatus.code == code) {
                return approvalStatus.code;
            }
        }
        //不存在返回0
        return 0;
    }
}
