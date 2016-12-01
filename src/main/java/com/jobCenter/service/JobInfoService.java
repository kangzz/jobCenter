package com.jobCenter.service;


import com.jobCenter.model.authority.logon.UserAccount;
import com.jobCenter.model.param.JobInfoSaveParam;
import com.jobCenter.model.param.JobInfoSearchParam;

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
}

