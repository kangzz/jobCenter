package com.jobCenter.listener;

import com.jobCenter.comm.SystemConstant;
import com.jobCenter.domain.HeartBeatInfo;
import com.jobCenter.enums.HeartType;
import com.jobCenter.enums.IsType;
import com.jobCenter.service.IJobService;
import com.jobCenter.util.SpringTool;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

/**
 * 描述：抢占当前机器为主机监听 如果抢占成功 开始心跳数据更新
 * 作者 ：kangzz
 * 日期 ：2016-03-19 02:02:18
 */
public class GrabToMasterListener implements ServletContextListener {

    private GrabToMasterListenerThread myThread;


    public void contextDestroyed(ServletContextEvent e) {
        if (myThread != null && myThread.isInterrupted()) {
            myThread.interrupt();
        }
    }

    public void contextInitialized(ServletContextEvent e) {
        String str = null;
        if (str == null && myThread == null) {
            myThread = new GrabToMasterListenerThread();
            myThread.start(); // servlet 上下文初始化时启动 socket
        }
    }
}

/**
 * 描述：自定义一个 Class 线程类继承自线程类，重写 run() 方法，用于从后台获取并处理数据
 * 作者 ：kangzz
 * 日期 ：2016-03-19 02:03:05
 */
class GrabToMasterListenerThread extends Thread {

    private final static Logger logger = Logger.getLogger(GrabToMasterListenerThread.class);
    //启动时校验是否已有初始化心跳信息 如果没有 需要初始化 默认需要初始化
    Boolean initHeatInfo = true;

    public void run() {

        Thread.currentThread().setName("GrabToMasterListenerThread");
        try {
            Thread.sleep(SystemConstant.GRAD_TO_MASTER_LISTENER_WAIT_TIME); //首次加载多等待20秒

        } catch (InterruptedException e) {
            logger.error("线程等待异常!", e);
        }
        IJobService jobService = (IJobService) SpringTool.getBean("jobService");
        if (initHeatInfo) {
            //初始化加载一条主机数据
            HeartBeatInfo initInfo = new HeartBeatInfo();
            initInfo.setMasterIdentity(SystemConstant.MASTER_IDENTITY);
            initInfo.setHeartType(HeartType.JOB_CENTER.getValue());
            initInfo.setHeartMaxVal(SystemConstant.HEAR_MAX_VAL);
            initInfo.setLastModifyTime(new Date());
            initInfo.setIsDel(IsType.NO.getValue());
            jobService.initHeartBeatInfo(initInfo);
            initHeatInfo = false;
            //logger.info("初始加载一条心跳数据!");
        }
        while (!this.isInterrupted()) {// 线程未中断执行循环
            try {
                Thread.sleep(SystemConstant.HEAR_RATE); //5秒一次心跳
                logger.info("心跳时间间隔:"+SystemConstant.HEAR_RATE+"毫秒");
            } catch (InterruptedException e) {
                logger.error("心跳时间睡眠异常!", e);
            }
            //是否需要移除任务信息
            Boolean isNeedRemoveJobs = false;
            //抢占主机条件:心跳类型 当前机器标志
            HeartBeatInfo info = new HeartBeatInfo();
            info.setHeartType(HeartType.JOB_CENTER.getValue());
            info.setMasterIdentity(SystemConstant.MASTER_IDENTITY);
            //检查是否切换成功 切换成功需要加载任务到内存 同时更新心跳时间
            //超时抢占标志
            Boolean outTimeChangeSuccess = jobService.changeToMaster(info);
            //如果切换成功 而且当前机器不是主机 那么要加载任务信息
            if (outTimeChangeSuccess && !SystemConstant.LOCAL_IS_MASTER) {
                logger.info("抢占成功,开始加载定时任务信息!");
                //判断是否加载成功
                if(jobService.loadAllJobListForMaster()) {
                    SystemConstant.LOCAL_IS_MASTER = true;
                    logger.info("加载定时任务信息成功,设置本机为主机!");
                }else{
                    logger.info("抢占成功,但是加载定时任务失败!");
                    SystemConstant.LOCAL_IS_MASTER = false;
                    //移除本机定时任务信息
                    isNeedRemoveJobs = true;
                }
            }else if(SystemConstant.LOCAL_IS_MASTER){
                //校验当前机器是否为主机
                HeartBeatInfo heartBeatInfo = new HeartBeatInfo();
                heartBeatInfo.setMasterIdentity(SystemConstant.MASTER_IDENTITY);
                //检查本机是否为主机 同时更新心跳时间
                Boolean isMaster = jobService.cheakIsMasterAndUpdateHeartBeat(heartBeatInfo);
                if(isMaster){
                    logger.info("当前主机信息为:"+SystemConstant.MASTER_IDENTITY);
                }else{
                    SystemConstant.LOCAL_IS_MASTER = false;
                    logger.info("抢占成功,但是心跳失败!");
                    //删除本机定时任务信息
                    isNeedRemoveJobs = true;
                }
            }else{//如果抢占失败 那么要移除所有的任务
                SystemConstant.LOCAL_IS_MASTER = false;
                //删除本机定时任务信息
                isNeedRemoveJobs = true;
                logger.info("抢占失败!");
            }
            //判断是否需要移除任务信息
            if(isNeedRemoveJobs){
                jobService.removeAllJobs();
                logger.info("移除所有的任务信息!");
            }
        }
    }
}
