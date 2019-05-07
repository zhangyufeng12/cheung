package com.example.didi.dto;
/*
*@author  zhangyufeng
*@data 2018/9/3 下午4:43
*/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class TcRespDto<T> implements Serializable {
    private Boolean result;
    private String errorCode;
    private String errorMessage;
    private JSONObject verifyReason;
    private T data;
    private static final boolean SUCCESS = true;
    private static final boolean FAIL = false;

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode( String errorCode ) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage( String errorMessage ) {
        this.errorMessage = errorMessage;
    }

    public TcRespDto() {
    }

    public TcRespDto( boolean result ) {
        this.result = Boolean.valueOf( result );
    }

    public TcRespDto( boolean result, T data ) {
        this.result = Boolean.valueOf( result );
        this.data = data;
    }

    public TcRespDto( boolean result, T data, JSONObject verifyReason ) {
        this.result = Boolean.valueOf( result );
        this.data = data;
        this.verifyReason = verifyReason;
    }

    public Boolean getResult() {
        return this.result;
    }

    public void setResult( Boolean result ) {
        this.result = result;
    }

    public JSONObject getVerifyReason() {
        return this.verifyReason;
    }

    public void setVerifyReason( JSONObject verifyReason ) {
        this.verifyReason = verifyReason;
    }

    public T getData() {
        return this.data;
    }

    public void setData( T data ) {
        this.data = data;
    }

    public static TcRespDto success() {
        return new TcRespDto( true );
    }

    public static <T> TcRespDto<T> success( T data ) {
        return new TcRespDto( true, data );
    }

    public static TcRespDto fail( JSONObject verifyReason ) {
        return new TcRespDto( false, verifyReason );
    }

    public String toString() {
        return JSON.toJSONString( this );
    }

}
