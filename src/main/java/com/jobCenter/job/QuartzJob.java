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
        jobService.sendJobRequest((JobInfoModel) arg0.getJobDetail().getJobDataMap().get("jobInfoMode"));
    }
}
