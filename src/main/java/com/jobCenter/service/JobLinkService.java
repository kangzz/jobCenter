package com.jobCenter.service;


import com.jobCenter.domain.JobLinkInfo;

import java.util.List;

/**
 * 描述：操作任务信息接口
 * 作者 ：kzz
 * 日期 ：2016-03-18 00:42:45
 */
public interface JobLinkService {
    List<JobLinkInfo> queryJobLinkList(JobLinkInfo jobLinkInfo);
}

