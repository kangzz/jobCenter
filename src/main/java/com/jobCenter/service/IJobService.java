package com.jobCenter.service;


import com.jobCenter.domain.HeartBeatInfo;
import com.jobCenter.domain.JobExecuteResult;
import com.jobCenter.model.JobInfoModel;

import java.util.List;

/**
 * 描述：操作任务信息接口
 * 作者 ：kzz
 * 日期 ：2016-03-18 00:42:45
 */
public interface IJobService {
    /**
     * 描述： 初始化如无数据需要加载
     * 作者 ：kangzz
     * 日期 ：2016-03-19 01:30:16
     */
    void initHeartBeatInfo(HeartBeatInfo heartBeatInfo);
    /**
     * 描述：检查当前机器是否是主机 通过根据主机标志id修改成功记录是否为0判断
     * 作者 ：kangzz
     * 日期 ：2016-03-18 23:25:16
     */
    Boolean cheakIsMasterAndUpdateHeartBeat(HeartBeatInfo heartBeatInfo);

    /**
     * 描述：切换当前机器为主机是否成功 通过根据超时时间修改任务成功记录是否为0判断
     * 作者 ：kangzz
     * 日期 ：2016-03-18 23:25:49
     */
    Boolean changeToMaster(HeartBeatInfo heartBeatInfo);

    /**
     * 描述：加载所有的定时任务到内存中
     * 作者 ：kangzz
     * 日期 ：2016-03-19 02:11:42
     */
    Boolean loadAllJobListForMaster();

    /**
     * 描述：获取所有的定时任务信息
     * 作者 ：kangzz
     * 日期 ：2016-03-18 23:26:38
     */
    List<JobInfoModel> getAllJobInfo();
    /**
     * 描述：移除所有的定时任务信息
     * 作者 ：kangzz
     * 日期 ：2016-03-22 09:42:26
     */
    Boolean removeAllJobs();
    /**
     * 描述：保存任务执行日志
     * 作者 ：kangzz
     * 日期 ：2016-03-22 22:13:49
     */
    void saveJobExecuteResult(JobExecuteResult record);
    /**
     * 描述：更新任务执行日志
     * 作者 ：kangzz
     * 日期 ：2016-03-22 22:27:56
     */
    void updateJobExecuteResultByUuid(JobExecuteResult record);
}

