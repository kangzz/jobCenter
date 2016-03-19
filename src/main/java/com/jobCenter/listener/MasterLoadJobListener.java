package com.jobCenter.listener;

import com.jobCenter.domain.HeartBeatInfo;
import com.jobCenter.enums.HeartType;
import com.jobCenter.enums.IsType;
import com.jobCenter.service.IJobService;
import com.jobCenter.util.SpringTool;
import com.jobCenter.comm.SystemConstant;
import com.xiaoleilu.hutool.system.SystemUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

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
    private final static Logger logger = Logger.getLogger(SlaveChangeToMasterThread.class);
    //是否首次加载 如果首次加载需要走初始化方法 否则 直接走心跳方法 并且只有首次加载所有任务
    Boolean isFirst = true;
    public void run() {
        Thread.currentThread().setName("master_heart_thread");
        try {
            Thread.sleep(SystemConstant.MASTER_LISTENER_WAIT_TIME); //等十秒再执行 加载Spring需要时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IJobService jobService = (IJobService) SpringTool.getBean("jobService");
        while (!this.isInterrupted()) {// 线程未中断执行循环
            if(isFirst) {
                //初始化加载一条主机数据
                HeartBeatInfo initInfo = new HeartBeatInfo();
                initInfo.setMasterIdentity(SystemConstant.MASTER_IDENTITY);
                initInfo.setHeartType(HeartType.JOB_CENTER.getValue());
                initInfo.setHeartMaxVal(SystemConstant.HEAR_MAX_VAL);
                initInfo.setLastModifyTime(new Date());
                initInfo.setIsDel(IsType.NO.getValue());
                jobService.initHeartBeatInfo(initInfo);
            }
            try {
                Thread.sleep(SystemConstant.HEAR_RATE); //5秒心跳一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //校验当前机器是否为主机
            HeartBeatInfo heartBeatInfo = new HeartBeatInfo();
            heartBeatInfo.setMasterIdentity(SystemConstant.MASTER_IDENTITY);
            //判断当前主机是否为主机 若为主机 同步更新最后心跳时间
            Boolean isMaster = jobService.cheakIsMaster(heartBeatInfo);
            //如果是主服务器 并且是首次加载 那么加载服务到内存 如果加载成功 那么这个监听的任务就结束了
            if (isMaster && isFirst) {
                //如果加载成功了 那么不再加载任务 否则就会重试加载
                isFirst = jobService.loadAllJobListForMaster() ? false : true;
            }else if(isMaster && !isFirst){
                logger.info("主机心跳记录_当前主机信息为:"+SystemConstant.MASTER_IDENTITY);
            }else {
                //不是主机就不要再跑这个线程了 因为切换主机监听的方法已经有加载所有任务的方法
                MasterLoadJobThread.currentThread().interrupt();
            }
        }
    }
}
