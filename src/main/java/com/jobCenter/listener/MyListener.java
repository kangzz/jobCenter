package com.jobCenter.listener;

import com.jobCenter.model.JobInfoModel;
import com.jobCenter.service.IJobService;
import com.jobCenter.util.SpringTool;
import com.jobCenter.job.QuartzJob;
import com.jobCenter.job.QuartzManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Listener的方式在后台执行一线程
 *
 * @author Champion.Wong  implements ServletContextListener
 *
 */
public class MyListener  {
    private MyThread myThread;



    public void contextDestroyed(ServletContextEvent e) {
        if (myThread != null && myThread.isInterrupted()) {
            myThread.interrupt();
        }
    }

    public void contextInitialized(ServletContextEvent e) {
        String str = null;
        if (str == null && myThread == null) {
            myThread = new MyThread();
            myThread.start(); // servlet 上下文初始化时启动 socket
        }
    }
}

/**
 * 自定义一个 Class 线程类继承自线程类，重写 run() 方法，用于从后台获取并处理数据
 *
 * @author Champion.Wong
 *
 */
class MyThread extends Thread {
    private String flag = "0";
    public void run() {
        while (!this.isInterrupted()) {// 线程未中断执行循环
            try {
                Thread.sleep(10000); //每隔2000ms执行一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            IJobService jobService = (IJobService) SpringTool.getBean("jobService");
            List<JobInfoModel> jobInfos =  jobService.getAllJobInfo();

            System.out.println("监听器输出数据库数据:"+jobInfos.size());

            if(flag.equals("0")) {
                flag = "";
                QuartzJob quartzJob = new QuartzJob();
                String job_name = "动态任务调度";
                System.out.println("【系统启动】开始(每1秒输出一次)...");
                JobInfoModel jobInfoMode = new JobInfoModel();
                QuartzManager.addJob(job_name + "1", quartzJob.getClass(), "0/1 * * * * ?", jobInfoMode);
            }
//			 ------------------ 开始执行 ---------------------------
            //System.out.println("____FUCK TIME:" + System.currentTimeMillis());
        }
    }
}
