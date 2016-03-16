package com.jobCenter.listener;

import com.jobCenter.domain.User;
import com.jobCenter.mapper.UserMapper;
import com.jobCenter.model.JobInfo;
import com.jobCenter.service.IUserService;
import com.jobCenter.util.SpringTool;
import com.jobCenter.web.QuartzJob;
import com.jobCenter.web.QuartzManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listener的方式在后台执行一线程
 *
 * @author Champion.Wong
 *
 */
public class MyListener implements ServletContextListener {
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
            IUserService userService = (IUserService) SpringTool.getBean("userService");
            User aa;
            aa = userService.getUserById("1");
            System.out.println("监听器输出数据库数据:"+aa.getName());

            if(flag.equals("0")) {
                flag = "";
                QuartzJob quartzJob = new QuartzJob();
                JobInfo jobInfo1 = new JobInfo();
                jobInfo1.setJobUrl("url1");
                jobInfo1.setJobName(aa.getName());
                jobInfo1.setJobType("1");
                String job_name = "动态任务调度";
                System.out.println("【系统启动】开始(每1秒输出一次)...");
                QuartzManager.addJob(job_name + "1", quartzJob.getClass(), "0/1 * * * * ?", jobInfo1);
            }
//			 ------------------ 开始执行 ---------------------------
            //System.out.println("____FUCK TIME:" + System.currentTimeMillis());
        }
    }
}
