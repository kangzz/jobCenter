/**
 * @Description:
 * @Title: QuartzJob.java
 * @Package com.joyce.quartz
 * @Copyright: Copyright (c) 2014
 * @author Comsys-LZP
 * @date 2014-6-26 下午03:37:11
 * @version V2.0
 */
package com.jobCenter.job;

import com.alibaba.fastjson.JSONObject;
import com.jobCenter.comm.SystemConstant;
import com.jobCenter.domain.JobExecuteResult;
import com.jobCenter.enums.JobExecuteType;
import com.jobCenter.model.JobInfoModel;
import com.jobCenter.model.JobLinkInfoModel;
import com.jobCenter.service.IJobService;
import com.jobCenter.util.HttpPoster;
import com.jobCenter.util.MD5Util;
import com.jobCenter.util.SpringTool;
import com.jobCenter.util.StringUtil;
import com.jobCenter.util.http.MessageUtil;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 描述：任务执行类
 * 作者 ：kangzz
 * 日期 ：2016-03-19 03:39:27
 */
public class QuartzJob implements Job {


    IJobService jobService = (IJobService) SpringTool.getBean("jobService");

    private static final Logger logger = Logger.getLogger(QuartzJob.class);

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        //发送请求
        sendRequest((JobInfoModel) arg0.getJobDetail().getJobDataMap().get("jobInfoMode"));
    }

    /**
     * 描述：发送请求方法
     * 作者 ：kangzz
     * 日期 ：2016-03-19 11:49:40
     */
    private void sendRequest(JobInfoModel jobInfoModel) {
        Thread.currentThread().setName("job_center_send_request_job_id:" + jobInfoModel.getJobId());
        Date nowDate = new Date();
        //如果任务不在有效期内 那么不用执行了
        if (!jobInfoModel.getJobStartTime().before(nowDate) || !jobInfoModel.getJobEndTime().after(nowDate)) {
            logger.info("定时任务不在有效期内!");
            return;
        }
        long startTime = System.currentTimeMillis();
        //任务id
        String jobId = String.valueOf(jobInfoModel.getJobId());
        //任务名称
        String jobName = jobInfoModel.getJobName();
        logger.info("任务[" + jobName + "]请求开始时间:" + startTime);
        //0 只执行一台 1 全部执行
        Integer jobExecuteType = jobInfoModel.getJobExecuteType();
        //需要保证通知成功时最大重试次数 这里用的是总调用次数 需要在重试基础上加1
        Integer jobRetryTimes = jobInfoModel.getJobRetryTimes() + 1;
        //获取主任务下的所有子任务信息
        List<JobLinkInfoModel> jobLinkInfoModels = jobInfoModel.getJobLinkInfoModels();
        int linkModelSize = jobLinkInfoModels != null ? jobLinkInfoModels.size() : 0;
        //遍历所有的子任务链接信息
        for (int i = 0; i < linkModelSize; i++) {
            //获取子任务信息
            JobLinkInfoModel jobLinkInfoModel = jobLinkInfoModels.get(i);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            //获取子任务id
            String jobLinkId = String.valueOf(jobLinkInfoModel.getJobLinkId());
            //封装加密字符串信息 加密字符串使用两个uuid截取部分拼装后再加密
            String uuid = UUID.randomUUID().toString();
            int jobLinkIdSubCount = jobLinkId.length() / SystemConstant.MD5_RATIO;
            jobLinkIdSubCount = jobLinkIdSubCount == 0 ? jobLinkId.length() : jobLinkIdSubCount;
            int uuidSubCount = uuid.length() / SystemConstant.MD5_RATIO;
            uuidSubCount = uuidSubCount == 0 ? uuid.length() : uuidSubCount;
            String securityStr = jobLinkId.substring(0, jobLinkIdSubCount) + uuid.substring(0, uuidSubCount);

            paramMap.put("securityCode",MD5Util.encodeMD5(securityStr,SystemConstant.MD5_KEY));
            paramMap.put("uuid", uuid);//唯一标志本次请求id
            paramMap.put("jobId", jobId);//任务主id
            paramMap.put("linkId", jobLinkId);//子任务id
            paramMap.put("serviceName",jobLinkInfoModel.getServiceName());//子任务执行的service
            //转换发送参数
            String param = MessageUtil.getParameter(paramMap);
            //获取请求url信息
            String sendUrl = jobLinkInfoModel.getJobLink();

            logger.info("本次定时任务调用地址：" + sendUrl);

            logger.info("本次定时任务调用参数：" + param);

            //本次请求是否成功
            Boolean sendIsSuccess = false;
            String jsonStr = StringUtil.EMPTY;
            for (int j = 0; j < jobRetryTimes; j++) {
                jsonStr = HttpPoster.postWithRes(sendUrl, param);
                logger.info("任务[" + jobInfoModel.getJobName() +"_jobLinkId:"+jobLinkId+ "]第" + (j+1) + "次调用返回值:" + jsonStr);
                if (checkIsSuccess(jsonStr)) {
                    sendIsSuccess = true;
                    JobExecuteResult record = new JobExecuteResult();
                    record.setJobStartTime(new Date());
                    record.setJobId(jobId);
                    record.setJobLinkId(jobLinkId);
                    record.setJobUuid(uuid);
                    jobService.saveJobExecuteResult(record);
                    break;
                } else {
                    continue;
                }
            }
            //如果需要保证通知成功 那么最多要遍历重试次数N次 如果中间有数据返回那么就break
            if (JobExecuteType.ALL.getValue() == jobExecuteType) {
                continue;
            } else if (!sendIsSuccess) {//如果不是全部通知 那么要循环子任务下的其他链接 看是否有可以成功的
                continue;
            } else {
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        logger.info("任务[" + jobInfoModel.getJobName() + "]请求结束时间:" + endTime);

        logger.info("任务[" + jobInfoModel.getJobName() + "]请求总耗时:" + (endTime-startTime)+"毫秒");
    }

    /**
     * 描述：校验调用返回参数是否成功
     * 作者 ：kangzz
     * 日期 ：2016-03-19 21:16:04
     */
    private Boolean checkIsSuccess(String returnJson) {
        if (StringUtil.isBlank(returnJson)) {
            return false;
        } else {
            JSONObject jsonObj = JSONObject.parseObject(returnJson);
            if (!jsonObj.containsKey(StringUtil.STATUS)) {
                return false;
            }
            if (StringUtil.SUCCESS.equalsIgnoreCase(jsonObj.getString(StringUtil.STATUS))) {
                return true;
            } else {
                return false;
            }
        }
    }

}
