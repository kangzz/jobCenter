package com.jobCenter.service;

import com.jobCenter.domain.JobInfo;
import com.jobCenter.model.JobInfoSearchParam;

import java.util.List;

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
    List<JobInfo> queryJobListByJobInfoSearchParam(JobInfoSearchParam jobInfoSearchParam);
}

