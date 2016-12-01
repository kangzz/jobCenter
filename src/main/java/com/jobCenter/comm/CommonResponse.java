package com.jobCenter.comm;

import com.alibaba.fastjson.JSONObject;

/**
 * 描述：返回数据
 * 作者 ：kangzz
 * 日期 ：2016-12-01 14:25:36
 */

public class CommonResponse<T> {

//    失败：
//    {"status":"failure","error_code":10001,"error_message":"this is a error","data":[]}
//    成功：【键值对】
//    {"status":"success","error_code":0,"error_message":"","data":{"COLUMN1":"I AM A STRING","COLUMN2":"I AM ALSO A STRING"}}
//    成功：【单值】
//    {"status":"success","error_code":0,"error_message":"","data":["I AM A STRING"]}
//    成功：【数组】
//    {"status":"success","error_code":0,"error_message":"","data":[{"COLUMN1.1":"STRING1.1","COLUMN1.2":"STRING1.2"},{"COLUMN2.1":"STRING1.1","COLUMN2.2":"STRING2.2"}]}


    private int error_code = 0;
    private String error_message = "成功";
    private String status = "success";

    private T data;

    public int geterror_code() {
        return error_code;
    }

    public void setErrorCode(int errorCode) {
        this.error_code = errorCode;
    }

    public String geterror_message() {
        return error_message;
    }

    public void setErrorMessage(String errorMessage) {
        this.error_message = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        JSONObject errorJson = new JSONObject();
        errorJson.put("status", status);
        errorJson.put("data", this.data);
        errorJson.put("error_message", error_message);
        errorJson.put("error_code", error_code);
        return errorJson.toString();
    }
}