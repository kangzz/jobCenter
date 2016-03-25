package com.jobCenter.service;


import com.jobCenter.domain.HeartBeatInfo;
import com.jobCenter.domain.JobExecuteResult;
import com.jobCenter.model.JobInfoModel;
import com.jobCenter.model.JobWarningModel;
import com.jobCenter.model.JobWarningPersonModel;

import java.util.List;

/**
 * 描述：操作任务信息接口
 * 作者 ：kzz
 * 日期 ：2016-03-18 00:42:45
 */
public interface JobService {
    /**
     * 描述： 初始化如无数据需要加载
     * 作者 ：kangzz
     * 日期 ：2016-03-19 01:30:16
     */
    void initHeartBeatInfo(HeartBeatInfo heartBeatInfo) throws Exception;
    /**
     * 描述：检查当前机器是否是主机 通过根据主机标志id修改成功记录是否为0判断
     * 作者 ：kangzz
     * 日期 ：2016-03-18 23:25:16
     */
    Boolean checkIsMasterAndUpdateHeartBeat(HeartBeatInfo heartBeatInfo)throws Exception;

    /**
     * 描述：切换当前机器为主机是否成功 通过根据超时时间修改任务成功记录是否为0判断
     * 作者 ：kangzz
     * 日期 ：2016-03-18 23:25:49
     */
    Boolean changeToMaster(HeartBeatInfo heartBeatInfo)throws Exception;

    /**
     * 描述：加载所有的定时任务到内存中
     * 作者 ：kangzz
     * 日期 ：2016-03-19 02:11:42
     */
    Boolean loadAllJobListForMaster()throws Exception;

    /**
     * 描述：获取所有的定时任务信息
     * 作者 ：kangzz
     * 日期 ：2016-03-18 23:26:38
     */
    List<JobInfoModel> getAllJobInfo()throws Exception;
    /**
     * 描述：移除所有的定时任务信息
     * 作者 ：kangzz
     * 日期 ：2016-03-22 09:42:26
     */
    Boolean removeAllJobs()throws Exception;

    /**
     * 描述：执行任务 发送任务请求
     * 作者 ：kangzz
     * 日期 ：2016-03-23 00:13:36
     */
    void sendJobRequest(JobInfoModel jobInfoModel)throws Exception;

    /**
     * 描述：保存任务执行日志
     * 作者 ：kangzz
     * 日期 ：2016-03-22 22:13:49
     */
    void saveJobExecuteResult(JobExecuteResult record)throws Exception;
    /**
     * 描述：更新任务执行日志
     * 作者 ：kangzz
     * 日期 ：2016-03-22 22:27:56
     */
    void updateJobExecuteResultByUuid(JobExecuteResult record) throws Exception;
    /**
     * 描述：为定时任务系统管理员报警
     * 作者 ：kangzz
     * 日期 ：2016-03-24 22:35:25
     */
    void notifyJobCenterManger(JobWarningModel jobWarningModel);
}

