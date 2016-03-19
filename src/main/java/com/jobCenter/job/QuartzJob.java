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

import com.jobCenter.enums.JobExecuteType;
import com.jobCenter.model.JobInfoModel;
import com.jobCenter.model.JobLinkInfoModel;
import com.jobCenter.util.CryptAES;
import com.jobCenter.util.HttpPoster;
import com.jobCenter.util.SystemConstant;
import com.jobCenter.util.http.MessageUtil;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 描述：任务执行类
 * 作者 ：kangzz
 * 日期 ：2016-03-19 03:39:27
 */
public class QuartzJob implements Job {

    private static final Logger logger = Logger.getLogger(QuartzJob.class);

    //private static final Byte[] lock = new Byte[0];

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

        logger.info("任务[" + jobInfoModel.getJobName() + "]请求开始时间:" + System.currentTimeMillis());


        logger.info("定时名称:" + jobInfoModel.getJobName());
        logger.info("jobLink:" + jobInfoModel.getJobLinkInfoModels().get(0).getJobLink());
        //0 只执行一台 1 全部执行
        Integer jobExecuteType = jobInfoModel.getJobExecuteType();

        List<JobLinkInfoModel> jobLinkInfoModels = jobInfoModel.getJobLinkInfoModels();
        int linkModelSize = jobLinkInfoModels != null ? jobLinkInfoModels.size() : 0;
        if (JobExecuteType.ALL.getValue() == jobExecuteType) {
            for (int i = 0; i < linkModelSize; i++) {
                JobLinkInfoModel jobLinkInfoModel = jobLinkInfoModels.get(i);
                Map<String, Object> paramMap = new HashMap<String, Object>();
                String jobLinkId = jobLinkInfoModel.getJobLinkId();
                String jobId = jobLinkInfoModel.getJobId();
                int jobLinkIdSubCount = jobLinkId.length() / SystemConstant.AES_RATIO;
                int jobIdSubCount = jobId.length() / SystemConstant.AES_RATIO;
                String securityStr = jobLinkId.substring(0, jobLinkIdSubCount) + jobId.substring(0, jobIdSubCount);
                paramMap.put("uuid", UUID.randomUUID());//唯一标志本次请求id
                paramMap.put("jobId", jobLinkInfoModel.getJobLinkId());//任务主id
                paramMap.put("linkId", jobLinkInfoModel.getJobLinkId());//子任务id
                paramMap.put("securityCode", CryptAES.AES_Encrypt(SystemConstant.AES_KEY, securityStr));//加密字符串
                String param = MessageUtil.getParameter(paramMap);

                String sendUrl = jobLinkInfoModel.getJobLink();

                logger.info("本次定时任务调用地址：" + sendUrl);
                logger.info("本次定时任务调用参数：" + param);

                String jsonStr = HttpPoster.postWithRes(sendUrl, param);

                logger.info("任务[" + jobInfoModel.getJobName() + "]调用返回值:" + jsonStr);

            }

        } else {
            for (int i = 0; i < linkModelSize; i++) {

            }
        }
        logger.info("任务[" + jobInfoModel.getJobName() + "]请求结束时间:" + System.currentTimeMillis());
    }

}
