/**
 * @Description: 
 *
 * @Title: QuartzTest.java
 * @Package com.joyce.quartz.main
 * @Copyright: Copyright (c) 2014
 *
 * @author Comsys-LZP
 * @date 2014-6-26 下午03:35:05
 * @version V2.0
 */
package web;


import com.jobCenter.model.JobInfo;
import com.jobCenter.web.QuartzJob;
import com.jobCenter.web.QuartzManager;

/**
 * @Description: 测试类
 *
 * @ClassName: QuartzTest
 * @Copyright: Copyright (c) 2014
 *
 * @author Comsys-LZP
 * @date 2014-6-26 下午03:35:05
 * @version V2.0
 */
public class QuartzTest {
	public static void main(String[] args) {
		try {
			QuartzJob quartzJob = new QuartzJob();
			JobInfo jobInfo1 = new JobInfo();
			jobInfo1.setJobUrl("url1");
			jobInfo1.setJobName("第一个定时任务");
			jobInfo1.setJobType("1");
			String job_name = "动态任务调度";
			System.out.println("【系统启动】开始(每1秒输出一次)...");  
			QuartzManager.addJob(job_name+"1", quartzJob.getClass(), "0/1 * * * * ?",jobInfo1);
			quartzJob = new QuartzJob();
			JobInfo jobInfo2 = new JobInfo();
			jobInfo2.setJobUrl("url1");
			jobInfo2.setJobName("第二个定时任务");
			jobInfo2.setJobType("1");
			QuartzManager.addJob(job_name+"2", quartzJob.getClass(), "0/1 * * * * ?",jobInfo2);
			Thread.sleep(5000);  
			System.out.println("【修改时间】开始(每2秒输出一次)...");  
			QuartzManager.modifyJobTime(job_name, "10/2 * * * * ?");  
			Thread.sleep(6000);  
			System.out.println("【移除定时】开始...");  
			QuartzManager.removeJob(job_name);  
			System.out.println("【移除定时】成功");  
			
			System.out.println("【再次添加定时任务】开始(每10秒输出一次)...");  
			QuartzManager.addJob(job_name, QuartzJob.class, "*/10 * * * * ?",null);
			Thread.sleep(60000);  
			System.out.println("【移除定时】开始...");  
			QuartzManager.removeJob(job_name);  
			System.out.println("【移除定时】成功");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
