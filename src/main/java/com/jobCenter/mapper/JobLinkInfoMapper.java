package com.jobCenter.mapper;


import com.jobCenter.domain.JobLinkInfo;

import java.util.List;

public interface JobLinkInfoMapper {
    int deleteByPrimaryKey(String jobLinkId);

    int insert(JobLinkInfo record);

    int insertSelective(JobLinkInfo record);

    JobLinkInfo selectByPrimaryKey(String jobLinkId);

    int updateByPrimaryKeySelective(JobLinkInfo record);

    int updateByPrimaryKey(JobLinkInfo record);

    List<JobLinkInfo> selectByJobLinkInfo(JobLinkInfo record);
}