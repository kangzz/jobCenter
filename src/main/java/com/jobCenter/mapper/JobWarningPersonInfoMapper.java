package com.jobCenter.mapper;

import com.jobCenter.domain.JobWarningPersonInfo;
import com.jobCenter.model.JobWarningPersonModel;

import java.util.List;

/**
 * 描述：定时任务报警人员信息
 * 作者 ：kangzz
 * 日期 ：2016-03-26 10:31:34
 */
public interface JobWarningPersonInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JobWarningPersonInfo record);

    int insertSelective(JobWarningPersonInfo record);

    JobWarningPersonInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JobWarningPersonInfo record);

    int updateByPrimaryKey(JobWarningPersonInfo record);

    /**
     * 描述：查询定时任务报警人员信息
     * 作者 ：kangzz
     * 日期 ：2016-03-25 10:36:24
     */
    List<JobWarningPersonInfo> selectByRecord(JobWarningPersonInfo record);

    /**
     * 描述：关联获取任务通知人员信息 支持根据jobId查询所有人员 和根据PersonType查询人员信息
     * 作者 ：kangzz
     * 日期 ：2016-03-25 10:48:15
     */
    List<JobWarningPersonModel> selectJobWarningPerson(JobWarningPersonModel jobWarningPersonModel);
}