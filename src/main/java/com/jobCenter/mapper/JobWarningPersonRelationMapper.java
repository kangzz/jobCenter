package com.jobCenter.mapper;


import com.jobCenter.domain.JobWarningPersonRelation;
import com.jobCenter.model.JobWarningPersonModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobWarningPersonRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JobWarningPersonRelation record);

    int insertSelective(JobWarningPersonRelation record);

    JobWarningPersonRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JobWarningPersonRelation record);

    int updateByPrimaryKey(JobWarningPersonRelation record);

    /**
     * 描述：根据任务id和报警类型获取所有的需要报警人员信息
     * 作者 ：kangzz
     * 日期 ：2016-03-23 20:23:13
     */
    List<JobWarningPersonModel> getWarningPersonByJobIdAndWarningType(@Param("jobId") Long jobId, @Param("warningType") Integer warningType);
}