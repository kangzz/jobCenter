package com.jobCenter.comm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jobCenter.util.ErrorCodeMsgUtil;
import com.jobCenter.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {

    private static Logger logger = LoggerFactory.getLogger(BaseController.class);
    /**
     * 失败返回
     * @param code
     * @return
     */
    protected CommonResponse errorReturn(int code) {
        CommonResponse response = new CommonResponse();
        response.setStatus("failure");
        response.setErrorCode(code);
        response.setErrorMessage(ErrorCodeMsgUtil.getMessage(code));
        response.setData(new JSONArray());
        logger.info(response.toString());
        return response;
    }


    /**
     * 成功返回
     * @param data
     * @return
     */
    protected CommonResponse successReturn(Object data) {
        CommonResponse response = new CommonResponse();
        response.setStatus("success");
        response.setErrorCode(0);
        response.setErrorMessage(ErrorCodeMsgUtil.getMessage(0));
        response.setData(data);
        logger.info(response.toString());
        return response;
    }
    
    /**
     * 使用此方法确保data中status和error_code有值
     * @param data
     * @return
     */
    protected CommonResponse commonReturn(JSONObject data) {
        CommonResponse response = new CommonResponse();
        response.setStatus(data.getString("status"));
        response.setErrorCode(Integer.parseInt(data.getString("error_code")));
        response.setData(data.get("data"));
        response.setErrorMessage(data.getString("error_message"));
        logger.info(response.toString());
        return response;
    }

    protected CommonResponse commonSuccessReturn(JSONObject data) {
        CommonResponse response = new CommonResponse();
        response.setStatus("success");
        response.setErrorCode(0);
        response.setData(data);
        response.setErrorMessage(ErrorCodeMsgUtil.getMessage(0));
        logger.info(response.toString());
        return response;
    }

    /**
     * 失败返回
     * @param code
     * @param args
     * @return
     */
    protected CommonResponse errorReturn(int code,Object[] args) {
        CommonResponse response = new CommonResponse();
        response.setStatus("failure");
        response.setErrorCode(code);
        response.setErrorMessage(ErrorCodeMsgUtil.getMessage(code,args));
        response.setData(new JSONArray());
        logger.info(response.toString());
        return response;
    }

    /**
     * 失败返回 自定义错误信息
     * @param code
     * @param message
     * @return
     */
    protected CommonResponse errorReturn(int code,String message) {
        CommonResponse response = new CommonResponse();
        response.setStatus("failure");
        response.setErrorCode(code);
        //如果未设置错误提示 则按照错误编码查询配置信息提示
        if(StringUtil.isBlank(message)){
            response.setErrorMessage(ErrorCodeMsgUtil.getMessage(code));
        }else{
            response.setErrorMessage(message);
        }
        response.setData(new JSONArray());
        logger.info(response.toString());
        return response;
    }
}
