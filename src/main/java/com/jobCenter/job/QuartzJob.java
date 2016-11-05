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

import com.jobCenter.model.JobInfoModel;
import com.jobCenter.model.JobWarningModel;
import com.jobCenter.service.JobService;
import com.jobCenter.util.NotifyWarningUtil;
import com.jobCenter.util.SpringTool;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * 描述：任务执行类
 * 作者 ：kangzz
 * 日期 ：2016-03-19 03:39:27
 */
public class QuartzJob implements Job {


    JobService jobService = (JobService) SpringTool.getBean("jobService");

    private static final Logger logger = Logger.getLogger(QuartzJob.class);

    /**
     * 描述：执行定时任务
     * 作者 ：kangzz
     * 日期 ：2016-03-24 22:43:00
     */
    public void execute(JobExecutionContext arg0) throws JobExecutionException, RuntimeException {
        //发送请求
        try {
            jobService.sendJobRequest((JobInfoModel) arg0.getJobDetail().getJobDataMap().get("jobInfoMode"));
        } catch (Exception e) {
            logger.error("定时任务发送请求异常", e);
            JobWarningModel jobWarningModel = new JobWarningModel();
            jobWarningModel.setWarningTitle("定时任务发送请求异常");
            jobWarningModel.setWarningContent(NotifyWarningUtil.getStackMsg(e));
            jobService.notifyJobCenterManger(jobWarningModel);
        }
    }
}
