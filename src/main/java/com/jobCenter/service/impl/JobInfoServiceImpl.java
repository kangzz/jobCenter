package com.jobCenter.service.impl;

import com.jobCenter.domain.JobInfo;
import com.jobCenter.mapper.*;
import com.jobCenter.model.dto.JobInfoDto;
import com.jobCenter.model.param.JobInfoSearchParam;
import com.jobCenter.service.JobInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service(value = "jobInfoService")
@Transactional
public class JobInfoServiceImpl implements JobInfoService {
    @Autowired
    private JobInfoMapper jobInfoMapper;

    private final static Logger logger = Logger.getLogger(JobInfoServiceImpl.class);
    /**
     * 描述：查询定时任务列表
     * 作者 ：kangzz
     * 日期 ：2016-11-28 21:28:34
     */
    public Map<String,Object> queryJobListByJobInfoSearchParam(JobInfoSearchParam jobInfoSearchParam){
        List<JobInfoDto> list = jobInfoMapper.queryJobListByJobInfoSearchParam(jobInfoSearchParam);
        Map<String, Object> result = new HashMap<String, Object>();
        long totalCount = jobInfoMapper.queryJobCountByJobInfoSearchParam(jobInfoSearchParam);;
        result.put("rows", list);
        result.put("total", totalCount);
        return result;
    }

}
