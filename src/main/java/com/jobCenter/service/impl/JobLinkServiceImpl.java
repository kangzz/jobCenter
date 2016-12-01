package com.jobCenter.service.impl;

import com.jobCenter.domain.JobLinkInfo;
import com.jobCenter.mapper.JobLinkInfoMapper;
import com.jobCenter.service.JobLinkService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(value = "jobLinkService")
@Transactional
public class JobLinkServiceImpl implements JobLinkService {
    @Autowired
    private JobLinkInfoMapper jobLinkInfoMapper;

    private final static Logger logger = Logger.getLogger(JobLinkServiceImpl.class);
    public List<JobLinkInfo> queryJobLinkList(JobLinkInfo jobLinkInfo){
        return jobLinkInfoMapper.selectByJobLinkInfo(jobLinkInfo);
    }


}
