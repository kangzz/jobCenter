package com.jobCenter.util.client;

import com.alibaba.fastjson.JSONObject;
import com.jobCenter.comm.SystemConstant;
import com.jobCenter.util.HttpPoster;
import com.jobCenter.util.MD5Util;
import com.jobCenter.util.StringUtil;
import com.jobCenter.util.http.MessageUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 描述：业务系统定时任务service需要继承的类
 * 作者 ：kangzz
 * 日期 ：2016-03-21 01:21:54
 */
public abstract class AbstractService implements Runnable  {

    private static final Logger logger = Logger.getLogger(JobCenterCommonAction.class);

    private String uuid;
    private String jobId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    /**
    * 描述：业务系统执行业务代码成功回调方法
    * 作者 ：kangzz
    * 日期 ：2016-03-22 23:12:23
    */
    public void successCallBackToJobCenter() {
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("uuid", uuid);//唯一标志本次请求id
            paramMap.put("status", "success");//执行成功标志
            paramMap.put("code", 0);//执行成功编码
            paramMap.put("message", "成功");//执行成功信息
            //转换发送参数
            String param = MessageUtil.getParameter(paramMap);
            //获取请求url信息
            String sendUrl = ClientSystemConstant.JOB_CENTER_CALL_BACK_URL;

            logger.info("本次回调JobCenter地址：" + sendUrl);

            logger.info("本次回调JobCenter参数：" + param);

            HttpPoster.postWithRes(sendUrl, param);

        } catch (Exception e) {
            logger.error("业务执行成功,回调JobCenter系统失败!",e);
        }
    }

    /**
     * 描述：业务系统执行业务代码成功回调方法
     * 作者 ：kangzz
     * 日期 ：2016-03-22 23:12:23
     */
    public void failCallBackToJobCenter(int code, String message) {
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("uuid", uuid);//唯一标志本次请求id
            paramMap.put("status", "fail");//任务执行失败
            paramMap.put("code", code);//失败错误码
            paramMap.put("message", message);//失败错误原因
            //转换发送参数
            String param = MessageUtil.getParameter(paramMap);
            //获取请求url信息
            String sendUrl = ClientSystemConstant.JOB_CENTER_CALL_BACK_URL;

            logger.info("本次回调JobCenter地址：" + sendUrl);

            logger.info("本次回调JobCenter参数：" + param);

            HttpPoster.postWithRes(sendUrl, param);

        } catch (Exception e) {
            logger.error("业务执行失败,回调JobCenter系统失败!",e);
        }
    }
}
