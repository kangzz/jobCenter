package com.jobCenter.service.impl;

import com.jobCenter.domain.JobInfo;
import com.jobCenter.mapper.*;
import com.jobCenter.model.*;
import com.jobCenter.service.JobInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(value = "jobInfoService")
@Transactional
public class JobInfoServiceImpl implements JobInfoService {
    @Autowired
    private JobInfoMapper jobInfoMapper;

    private final static Logger logger = Logger.getLogger(JobInfoServiceImpl.class);
    /**
     * 描述：查询定时任务列表
     * 作者 ：kangzz
     * 日期 ：2016-11-28 21:28:34
     */
    public List<JobInfo> queryJobListByJobInfoSearchParam(JobInfoSearchParam jobInfoSearchParam){
        return jobInfoMapper.queryJobListByJobInfoSearchParam(jobInfoSearchParam);
    }

}
