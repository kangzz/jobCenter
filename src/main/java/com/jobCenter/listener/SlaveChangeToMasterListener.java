package com.jobCenter.listener;

import com.jobCenter.domain.HeartBeatInfo;
import com.jobCenter.enums.HeartType;
import com.jobCenter.service.IJobService;
import com.jobCenter.util.SpringTool;
import com.jobCenter.comm.SystemConstant;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 描述：备机尝试切换为主机
 * 作者 ：kangzz
 * 日期 ：2016-03-19 02:02:18
 */
public class SlaveChangeToMasterListener implements ServletContextListener {

    private SlaveChangeToMasterThread myThread;


    public void contextDestroyed(ServletContextEvent e) {
        if (myThread != null && myThread.isInterrupted()) {
            myThread.interrupt();
        }
    }

    public void contextInitialized(ServletContextEvent e) {
        String str = null;
        if (str == null && myThread == null) {
            myThread = new SlaveChangeToMasterThread();
            myThread.start(); // servlet 上下文初始化时启动 socket
        }
    }
}

/**
 * 描述：自定义一个 Class 线程类继承自线程类，重写 run() 方法，用于从后台获取并处理数据
 * 作者 ：kangzz
 * 日期 ：2016-03-19 02:03:05
 */
class SlaveChangeToMasterThread extends Thread {

    private final static Logger logger = Logger.getLogger(SlaveChangeToMasterThread.class);

    //是否首次加载
    Boolean isFirst = true;

    public void run() {
        Thread.currentThread().setName("slave_change_to_master_thread");
        try {
            if (isFirst) {
                Thread.sleep(SystemConstant.SLAVE_TO_MASTER_WAIT_TIME); //首次加载多等待20秒
                isFirst = false;
            }
        } catch (InterruptedException e) {
            logger.error("备机尝试切换为主机异常!", e);
        }
        IJobService jobService = (IJobService) SpringTool.getBean("jobService");
        while (!this.isInterrupted()) {// 线程未中断执行循环
            try {
                if (!isFirst) {
                    Thread.sleep(SystemConstant.HEAR_RATE); //5秒一次心跳
                }
            } catch (InterruptedException e) {
                logger.error("备机尝试切换为主机异常!", e);
            }
            HeartBeatInfo info = new HeartBeatInfo();
            info.setHeartType(HeartType.JOB_CENTER.getValue());
            info.setMasterIdentity(SystemConstant.MASTER_IDENTITY);
            //检查是否切换成功 切换成功需要加载任务到内存 同时更新心跳时间
            Boolean changeSuccess = jobService.changeToMaster(info);
            if (changeSuccess) {
                logger.info("切换当前机器为主机成功!");
               if(jobService.loadAllJobListForMaster()){
                   SystemConstant.localIsMaster = true;
                    //加载成功 主机就不要尝试切换了
                    SlaveChangeToMasterThread.currentThread().interrupt();
                }else{
                   SystemConstant.localIsMaster = false;
               }
            }
        }
    }
}
