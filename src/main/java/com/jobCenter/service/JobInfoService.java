package com.jobCenter.service;


import com.jobCenter.domain.JobInfo;
import com.jobCenter.model.authority.logon.UserAccount;
import com.jobCenter.model.param.JobExecuteResultParam;
import com.jobCenter.model.param.JobInfoSaveParam;
import com.jobCenter.model.param.JobInfoSearchParam;

import java.util.List;
import java.util.Map;

/**
 * 描述：操作任务信息接口
 * 作者 ：kzz
 * 日期 ：2016-03-18 00:42:45
 */
public interface JobInfoService {
    /**
     * 描述：查询jobList
     * 作者 ：kangzz
     * 日期 ：2016-11-28 21:27:58
     */
    Map<String,Object> queryJobListByJobInfoSearchParam(JobInfoSearchParam jobInfoSearchParam);
    void saveJobInfo(JobInfoSaveParam jobInfoSaveParam, UserAccount userAccount);
    void deleteJobInfoById(String jobId,UserAccount userAccount);
    void changeJobValidById(String jobId, Integer isValid, UserAccount userAccount);
    JobInfoSaveParam getJobInfoToEdit(String jobId);
    List<JobInfo> queryJobInfoList(JobInfo jobInfo);
    Map<String,Object> queryJobExecuteListSearchParam(JobExecuteResultParam jobExecuteResultParam);
    /**
     * <pre>
     * desc : 立即执行任务
     * @author : kangzz
     * date : 2018-01-15 18:46:39
     *
     * @Param jobId 任务ID
     * @return
     * </pre>
     */
    void dealJobNowById(String jobId);
}

