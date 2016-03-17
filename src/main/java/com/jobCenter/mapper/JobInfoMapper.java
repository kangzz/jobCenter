package com.jobCenter.mapper;


import com.jobCenter.domain.JobInfo;
import com.jobCenter.model.JobInfoModel;

import java.util.List;

public interface JobInfoMapper {

    int deleteByPrimaryKey(String jobId);

    int insert(JobInfo record);

    int insertSelective(JobInfo record);

    JobInfo selectByPrimaryKey(String jobId);

    int updateByPrimaryKeySelective(JobInfo record);

    int updateByPrimaryKey(JobInfo record);

    List<JobInfo> selectByJobInfo(JobInfo record);
}