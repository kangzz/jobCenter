package com.jobCenter.mapper;


import com.jobCenter.domain.JobWarningPersonInfo;
import com.jobCenter.model.JobWarningPersonModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述：报警人员信息
 * 作者 ：kangzz
 * 日期 ：2016-03-23 20:08:43
 */
public interface JobWarningPersonInfoMapper {


    int deleteByPrimaryKey(Long id);

    int insert(JobWarningPersonInfo record);

    int insertSelective(JobWarningPersonInfo record);

    JobWarningPersonInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JobWarningPersonInfo record);

    int updateByPrimaryKey(JobWarningPersonInfo record);
}