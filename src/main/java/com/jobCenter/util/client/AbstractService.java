package com.jobCenter.util.client;

import com.jobCenter.enums.DoneStatus;
import com.jobCenter.enums.JobStatus;
import com.jobCenter.util.HttpPoster;
import com.jobCenter.util.http.MessageUtil;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：业务系统定时任务service需要继承的类
 * 作者 ：kangzz
 * 日期 ：2016-03-21 01:21:54
 */
public abstract class AbstractService implements Runnable  {

    private static final Logger logger = Logger.getLogger(JobCenterCommonController.class);

    private String uuid;//本次调用uuid
    private String jobId;//主任务id
    private String linkId;//任务子id
    private String jobName;//任务名称

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

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    /**
    * 描述：业务系统执行业务代码成功回调方法
    * 作者 ：kangzz
    * 日期 ：2016-03-22 23:12:23
    */
    public synchronized void successCallBackToJobCenter() {
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("uuid", uuid);//唯一标志本次请求id
            paramMap.put("jobId",jobId);//任务id
            paramMap.put("linkId",linkId);//子任务id
            paramMap.put("jobName",jobName);//任务名称
            paramMap.put("status", DoneStatus.ZXCG.getValue());//执行成功标志
            paramMap.put("code", JobStatus.ZXCG.getValue());//执行成功编码
            paramMap.put("message", JobStatus.ZXCG.getName());//执行成功信息
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
    public synchronized void failCallBackToJobCenter(String errorMessage) {
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("uuid", uuid);//唯一标志本次请求id
            paramMap.put("jobId",jobId);//任务id
            paramMap.put("linkId",linkId);//子任务id
            paramMap.put("jobName",jobName);//任务名称
            paramMap.put("status", DoneStatus.ZXSB.getValue());//任务执行失败
            paramMap.put("code", JobStatus.ZXSB.getValue());//失败错误码
            paramMap.put("message", errorMessage);//失败错误原因
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
