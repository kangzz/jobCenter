package com.jobCenter.mapper;


import com.jobCenter.domain.JobWarningPersonRelation;
import com.jobCenter.model.JobWarningPersonModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述： 查询任务与人员关系数据
 * 作者 ：kangzz
 * 日期 ：2016-03-26 10:24:50
 */
public interface JobWarningPersonRelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JobWarningPersonRelation record);

    int insertSelective(JobWarningPersonRelation record);

    JobWarningPersonRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JobWarningPersonRelation record);

    int updateByPrimaryKey(JobWarningPersonRelation record);


}