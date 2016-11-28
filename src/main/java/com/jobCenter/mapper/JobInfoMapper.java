package com.jobCenter.mapper;


import com.jobCenter.domain.JobInfo;
import com.jobCenter.model.JobInfoModel;
import com.jobCenter.model.JobInfoSearchParam;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobInfoMapper {

    int deleteByPrimaryKey(String jobId);

    int insert(JobInfo record);

    int insertSelective(JobInfo record);

    JobInfo selectByPrimaryKey(String jobId);

    int updateByPrimaryKeySelective(JobInfo record);

    int updateByPrimaryKey(JobInfo record);

    List<JobInfo> selectByJobInfo(JobInfo record);

    /**
     * 描述：任务列表界面获取任务信息
     * 作者 ：kangzz
     * 日期 ：2016-11-28 21:30:49
     */
    List<JobInfo> queryJobListByJobInfoSearchParam(JobInfoSearchParam jobInfoSearchParam);
}