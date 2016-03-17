/**
 * @Description: 
 *
 * @Title: QuartzJob.java
 * @Package com.joyce.quartz
 * @Copyright: Copyright (c) 2014
 *
 * @author Comsys-LZP
 * @date 2014-6-26 下午03:37:11
 * @version V2.0
 */
package com.jobCenter.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jobCenter.model.JobInfoModel;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Description: 任务执行类
 *
 * @ClassName: QuartzJob
 * @Copyright: Copyright (c) 2014
 *
 * @author Comsys-LZP
 * @date 2014-6-26 下午03:37:11
 * @version V2.0
 */
public class QuartzJob implements Job {
	private final static Logger logger = Logger.getLogger(QuartzJob.class);

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+ "★★★★★★★★★★★");
		//System.out.println(arg0.getJobDetail().getJobDataMap().get("url"));
		List<JobInfoModel> jobs = (List<JobInfoModel>)arg0.getJobDetail().getJobDataMap().get("jobInfos");
		System.out.println("定时任务输出数据库字段:"+jobs.size());
		logger.info("jobId="+jobs.get(0).getJobId());

	}
}
