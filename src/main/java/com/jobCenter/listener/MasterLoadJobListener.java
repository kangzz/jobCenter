package com.jobCenter.listener;

import com.jobCenter.domain.HeartBeatInfo;
import com.jobCenter.domain.HeartType;
import com.jobCenter.enums.IsType;
import com.jobCenter.job.QuartzJob;
import com.jobCenter.job.QuartzManager;
import com.jobCenter.model.JobInfoModel;
import com.jobCenter.service.IJobService;
import com.jobCenter.util.SpringTool;
import com.jobCenter.util.SystemConstant;
import com.xiaoleilu.hutool.system.SystemUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;
import java.util.List;

/**
 * 描述：主机加载任务数据监听
 * 作者 ：kangzz
 * 日期 ：2016-03-19 02:02:18
 */
public class MasterLoadJobListener implements ServletContextListener {

    private MasterLoadJobThread myThread;


    public void contextDestroyed(ServletContextEvent e) {
        if (myThread != null && myThread.isInterrupted()) {
            myThread.interrupt();
        }
    }

    public void contextInitialized(ServletContextEvent e) {
        String str = null;
        if (str == null && myThread == null) {
            myThread = new MasterLoadJobThread();
            myThread.start(); // servlet 上下文初始化时启动 socket
        }
    }
}
/**
 * 描述：自定义一个 Class 线程类继承自线程类，重写 run() 方法，用于从后台获取并处理数据
 * 作者 ：kangzz
 * 日期 ：2016-03-19 02:03:05
 */
class MasterLoadJobThread extends Thread {



    public void run() {
        try {
            Thread.sleep(10000); //每隔2000ms执行一次
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IJobService jobService = (IJobService) SpringTool.getBean("jobService");
        while (!this.isInterrupted()) {// 线程未中断执行循环
            //拼装主机标志
            String masterIdentity =
                    HeartType.JOB_CENTER.getValue() + ":"
                            + SystemUtil.getHostInfo().getName() + ":" + SystemUtil.getHostInfo().getAddress();


            //初始化加载一条主机数据
            HeartBeatInfo initInfo = new HeartBeatInfo();
            initInfo.setMasterIdentity(masterIdentity);
            initInfo.setHeartType(HeartType.JOB_CENTER.getValue());
            initInfo.setHeartMaxVal(SystemConstant.HEAR_MAX_VAL);
            initInfo.setLastModifyTime(new Date());
            initInfo.setIsDel(IsType.NO.getValue());
            jobService.initHeartBeatInfo(initInfo);

            //校验当前机器是否为主机
            HeartBeatInfo heartBeatInfo = new HeartBeatInfo();
            heartBeatInfo.setMasterIdentity(masterIdentity);
            Boolean isMaster = jobService.cheakIsMaster(heartBeatInfo);
            //如果是主服务器 那么加载服务到内存
            if (isMaster) {
                jobService.loadAllJobListForMaster();
                MasterLoadJobThread.currentThread().interrupt();
            } else {
                MasterLoadJobThread.currentThread().interrupt();
            }
        }
    }
}
