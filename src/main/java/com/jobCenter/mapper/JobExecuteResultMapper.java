package com.jobCenter.mapper;


import com.jobCenter.domain.JobExecuteResult;
import com.jobCenter.model.dto.JobExecuteResultDto;
import com.jobCenter.model.param.JobExecuteResultParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：执行计划记录表
 * @return
 * 作者 ：kangzz
 * 日期 ：2016-03-22 22:21:11
 */
public interface JobExecuteResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JobExecuteResult record);
    /**
     * 描述： 插入执行计划数据
     * 作者 ：kangzz
     * 日期 ：2016-03-22 22:20:13
     */
    int insertSelective(JobExecuteResult record);

    JobExecuteResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JobExecuteResult record);

    int updateByPrimaryKey(JobExecuteResult record);
    /**
     * 描述：根据当前任务唯一标志 更新执行数据结果
     * 作者 ：kangzz
     * 日期 ：2016-03-22 22:19:36
     */
    int updateByUuid(JobExecuteResult record);

    List<JobExecuteResultDto> queryJobExecuteListSearchParam(JobExecuteResultParam jobExecuteResultParam);
    long queryCountJobExecuteListSearchParam(JobExecuteResultParam jobExecuteResultParam);;
}