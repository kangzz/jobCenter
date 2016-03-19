package com.jobCenter.util.http.bean;

import com.alibaba.fastjson.JSONObject;


/**
 * 描述：返回json结果
 * 作者 ：kangzz
 * 日期 ：2016-03-19 10:41:46
 */
public class JsonResponse {
	
	/**
	 * 成功提示
	 */
	public final static String SUCCESS_MESSAGE = "成功";
	
	//返回码
	private String status;
	
	private String errorCode;
	
	private String errorMessage;
	
	//返回关键值
	private Object data;

	/**
	 * 判断是否成功
	 * @return
	 */
	public boolean success() {
		if ("success".equals(status)) {
			return true;
		}
		return false;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("error_code", errorCode);
		jsonObj.put("error_message", errorMessage);
		jsonObj.put("data", data);
		return jsonObj.toString();
	}
	public static JSONObject toJsonObject(JsonResponse jsonResponse) {
		if(jsonResponse == null){
			return null;
		}
		JSONObject jsonObj = new JSONObject();
		if(jsonResponse.getStatus() != null ){
			jsonObj.put("status", jsonResponse.getStatus());
		}
		if(jsonResponse.getErrorCode() != null){
			jsonObj.put("error_code", jsonResponse.getErrorCode());
		}
		if(jsonResponse.getErrorMessage() != null){
			jsonObj.put("error_message", jsonResponse.getErrorMessage());
		}
		if(jsonResponse.getData() != null){
			jsonObj.put("data", jsonResponse.getData());
		}
		return jsonObj;
	}
	
}
