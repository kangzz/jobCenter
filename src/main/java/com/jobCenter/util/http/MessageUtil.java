package com.jobCenter.util.http;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jobCenter.util.StringUtil;
import com.jobCenter.util.http.bean.HttpPoster;
import com.jobCenter.util.http.bean.JsonResponse;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Set;

/**
 * 描述：消息工具类
 * 作者 ：kangzz
 * 日期 ：2016-03-19 10:40:58
 */
public class MessageUtil {
	
	private static final Logger logger = Logger.getLogger(MessageUtil.class);
	
	public static JsonResponse getTransmessage(String requrl) {

		JsonResponse result = null;

		if (requrl == null) {
			logger.info("http 请求 地址 为 null");
			return result;
		}
		logger.info("请求url="+requrl);
		String strjson = null;

		try {
			strjson = HttpPoster.get(requrl);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		logger.info("getTransmessage HTTP 请求返回数据位[" + strjson + "]");
		if (strjson == null) {
			logger.info("http 请求 返回json 为 null");
			return result;
		}
		logger.info("http 请求 返回json 为 " + strjson);

		JSONObject jsonobj = parseJson(strjson);
		if (jsonobj == null) {
			return null;
		}

		result = new JsonResponse();
		String status = "failure";//success/failure
		String errorCode = null;
		String errorMessage = null;
		String dataStr = null;
		if (jsonobj.containsKey("status"))  {
			status = jsonobj.getString("status");
		}
		
		if (jsonobj.containsKey("error_code")) {
			errorCode = jsonobj.getString("error_code");
		}
		
		if (jsonobj.containsKey("error_message")) {
			errorMessage = jsonobj.getString("error_message");
		}
		if (jsonobj.containsKey("data")) {
			dataStr = jsonobj.getString("data");
		}
		
		if(!"success".equals(status.toLowerCase())) {
			logger.info("getTransmessage 服务器返回错误，返回状态吗： " + errorCode + "，报错信息：" + errorMessage);
		}
		
		result.setStatus(status);
		result.setErrorCode(errorCode);
		result.setErrorMessage(errorMessage);
		result.setData(dataStr);
		
		return result;
	}
	
	public static String getTransmessageStr(String requrl) {

		String result = null;

		if (requrl == null) {
			logger.info("http 请求 地址 为 null");
			return result;
		}
		logger.info("请求url="+requrl);
		String strjson = null;

		try {
			strjson = HttpPoster.get(requrl);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		logger.info("getTransmessage HTTP 请求返回数据位[" + strjson + "]");
		if (strjson == null) {
			logger.info("http 请求 返回json 为 null");
			return result;
		}
		logger.info("http 请求 返回json 为 " + strjson);
		result = strjson;

		
		return result;
	}
	
	
	public static String getTransmessageStrAgent(String requrl) {

		String result = null;

		if (requrl == null) {
			logger.info("http 请求 地址 为 null");
			return result;
		}
		logger.info("请求url="+requrl);
		String strjson = null;

		try {
			strjson = HttpPoster.getAgent(requrl);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		logger.info("getTransmessage HTTP 请求返回数据位[" + strjson + "]");
		if (strjson == null) {
			logger.info("http 请求 返回json 为 null");
			return result;
		}
		logger.info("http 请求 返回json 为 " + strjson);
		result = strjson;

		
		return result;
	}
	
	public static String getTransmessageJson(String requrl) {

		String result = null;

		if (requrl == null) {
			logger.info("http 请求 地址 为 null");
			return result;
		}
		logger.info("请求url="+requrl);
		String strjson = null;

		try {
			strjson = HttpPoster.get(requrl, "application/json", "UTF-8", true, null);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		logger.info("getTransmessage HTTP 请求返回数据位[" + strjson + "]");
		if (strjson == null) {
			logger.info("http 请求 返回json 为 null");
			return result;
		}
		logger.info("http 请求 返回json 为 " + strjson);
		result = strjson;

		
		return result;
	}
	
	
	/**
	 * 获取信息
	 * @param requrl 请求地址
	 * @return
	 */
	public static String postTransmessageStr(String requrl, String str) {
		String result = null;
		if (requrl == null) {
			logger.debug("http 请求 地址 为 null");
			return result;
		}
		logger.debug("请求url="+requrl);
		String strjson = null;

		try {
			strjson = HttpPoster.postWithRes(requrl, str);
		} catch (Exception e) {
			logger.error("postTransmessageStr 异常", e);
		}

		if (strjson == null) {
			logger.debug("http 请求 返回json 为 null");
			return result;
		}
		result = strjson;
		return result;
	}
	
	/**
	 * 获取信息
	 * @param requrl 请求地址
	 * @return
	 */
	public static String postTransmessageJson(String requrl, String str) {
		String result = null;
		if (requrl == null) {
			logger.debug("http 请求 地址 为 null");
			return result;
		}
		logger.debug("请求url="+requrl);
		String strjson = null;

		try {
			strjson = HttpPoster.postWithResJson(requrl, str);
		} catch (Exception e) {
			logger.error("postTransmessageStr 异常", e);
		}

		if (strjson == null) {
			logger.debug("http 请求 返回json 为 null");
			return result;
		}
		result = strjson;
		return result;
	}
	
	
	/**
	 * 获取信息
	 * @param requrl 请求地址
	 * @return
	 */
	public static JsonResponse postTransmessage(String requrl, String str) {

		JsonResponse result = null;

		if (requrl == null) {
			logger.debug("http 请求 地址 为 null");
			return result;
		}
		logger.debug("请求url="+requrl);
		String strjson = null;

		try {
			strjson = HttpPoster.postWithRes(requrl, str);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		if (strjson == null) {
			logger.debug("http 请求 返回json 为 null");
			return result;
		}

		JSONObject jsonobj = parseJson(strjson);
		if (jsonobj == null) {
			return null;
		}

		result = new JsonResponse();
		
		
		
		String status = "failure";//success/failure
		String errorCode = null;
		String errorMessage = null;
		Object dataStr = null;
		
		if (jsonobj.containsKey("status"))  {
			status = jsonobj.getString("status");
		}
		
		if (jsonobj.containsKey("error_code")) {
			errorCode = jsonobj.getString("error_code");
		}
		
		if (jsonobj.containsKey("error_message")) {
			errorMessage = jsonobj.getString("error_message");
		}
		if (jsonobj.containsKey("data")) {
			dataStr = jsonobj.get("data");
		}
		
		if(!"success".equals(status.toLowerCase())) {
			logger.info("getTransmessage 服务器返回错误，返回状态吗： " + errorCode + "，报错信息：" + errorMessage);
		}
		
		result.setStatus(status);
		result.setErrorCode(errorCode);
		result.setErrorMessage(errorMessage);
		result.setData(dataStr);;
		return result;
	}

	/**
	 * 解析请求的json串
	 * 
	 * @param jsonStr
	 * @return
	 */
	private static JSONObject parseJson(String jsonStr) {

		if (jsonStr == null || jsonStr.equals("")) {
			return null;
		}

		try {
			JSONObject json = JSONObject.parseObject(jsonStr);

			if (json != null && !json.isEmpty()) {
				return json;
			}
		} catch (JSONException e) {
			
		}

		return null;
	}
	
	/**
	 * 获取ams网站接口参数
	 * @param map 参数列表
	 * @return
	 */
	public static String getParameter(Map<String, Object> map) {
		try {
			String result = "";
			if (map == null) {
				return result;
			}
			
			Set<String> keys = map.keySet();
			for (String key : keys) {
				if(map.get(key) != null) {
					if(result != null && !"".equals(result)) {
						result = result + "&";
					}
					result = result + "" + key +"=" + map.get(key);
				}
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}


    /**
     * @作者 liuys
     * @param url 调用Url
     * @param paramMap 参数对象
     * @param type post/get
     * @param times 失败调用次数
     * @param successMessage 校验成功的字符串
     * @param errorMessage 校验失败的字符串
     * @return 返回值
     */
    public static String remoteOperate(String url, Map<String,Object> paramMap,String type,int times,String successMessage,String errorMessage){
        String jsonStr ="";
        String param = MessageUtil.getParameter(paramMap);
        if(type.equals("get")){
            url = url +"?"+param;
            try {
                jsonStr =  HttpPoster.get(url);
            } catch (Exception e) {
                //处理远程调用异常
                e.printStackTrace();
            }
        }else if(type.equals("post")){
            try {
                jsonStr = HttpPoster.postWithRes(url, param);
            } catch (Exception e) {
                //处理远程调用异常
                e.printStackTrace();
            }
        }else{

        }
        if(StringUtil.isBlank(jsonStr)){
            //未返回结果或者返回结果为空
        }
        //根据返回结果校验错误信息，如果返回结果中包含错误信息
        //记录错误调用日志，将调用信息记录到重发机制调用表
        //表字段有 ID URL TYPE PARAM TIMES succ_message error_message is_del add_time modify_time current_count
        //定时任务执行，定时执行is_del=0的记录。每次执行将当前次数current_count加1 ，调用成功，将is_del字段修改为1
        //如果调用失败，将错误信息记录到重发操作失败表
        //表字段有：ID PID(调用表ID) URL TYPE PARAM current_count ,result,succ_message,error_message,error_reason,add_time
        //当调用表中current_count值等于接口调用开始传入的次数时候，启动报警机制，邮件或者短信，由人工处理此单业务
        //本功能不做调用之外的业务操作，不对执行结果做业务处理
        return jsonStr;
    }
}
