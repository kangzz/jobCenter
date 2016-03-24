package com.jobCenter.service;


import com.jobCenter.domain.HeartBeatInfo;
import com.jobCenter.domain.JobExecuteResult;
import com.jobCenter.model.JobInfoModel;
import com.jobCenter.model.JobWarningModel;
import com.jobCenter.model.JobWarningPersonModel;

import java.util.List;

/**
 * 描述 :报警相关信息
 * 作者 ：kzz
 * 日期 ：2016-03-18 00:42:45
 */
public interface WarningService {


    void updateJobExecuteResultByUuid(JobWarningModel jobWarningModel,Throwable e);
}

