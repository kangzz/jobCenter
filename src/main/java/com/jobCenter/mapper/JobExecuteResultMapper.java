package com.jobCenter.mapper;


import com.jobCenter.domain.JobExecuteResult;

public interface JobExecuteResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JobExecuteResult record);

    int insertSelective(JobExecuteResult record);

    JobExecuteResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JobExecuteResult record);

    int updateByPrimaryKey(JobExecuteResult record);

    int updateByUuid(JobExecuteResult record);
}