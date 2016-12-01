package com.jobCenter.service.impl;

import com.jobCenter.domain.JobInfo;
import com.jobCenter.domain.JobLinkInfo;
import com.jobCenter.enums.IsType;
import com.jobCenter.mapper.JobInfoMapper;
import com.jobCenter.mapper.JobLinkInfoMapper;
import com.jobCenter.model.authority.logon.UserAccount;
import com.jobCenter.model.dto.JobInfoDto;
import com.jobCenter.model.param.JobInfoSaveParam;
import com.jobCenter.model.param.JobInfoSearchParam;
import com.jobCenter.service.JobInfoService;
import com.jobCenter.util.DateUtil;
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
    @Autowired
    private JobLinkInfoMapper jobLinkInfoMapper;

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

    public void saveJobInfo(JobInfoSaveParam jobInfoSaveParam, UserAccount userAccount){
        JobInfo jobInfo = this.getJobInfoForSave(jobInfoSaveParam, userAccount);
        if(jobInfo.getJobId() == null){
            jobInfoMapper.insertSelective(jobInfo);
        }else{
            jobInfoMapper.updateByPrimaryKeySelective(jobInfo);
        }
        this.saveJobLinkInfo(jobInfo,jobInfoSaveParam, userAccount);
    }
    private JobInfo getJobInfoForSave(JobInfoSaveParam jobInfoSaveParam, UserAccount userAccount){
        JobInfo jobInfo = new JobInfo();
        Integer jobId = jobInfoSaveParam.getJobId();
        jobInfo.setJobId(jobId);
        if(jobId == null){
            jobInfo.setCreateId(userAccount.getUserId()+"");
            jobInfo.setCreateTime(DateUtil.getCurrentDate());
        }
        jobInfo.setUpdateId(userAccount.getUserId()+"");
        jobInfo.setUpdateTime(DateUtil.getCurrentDate());

        jobInfo.setIsValid(jobInfoSaveParam.getIsValid());
        jobInfo.setJobSystem(jobInfoSaveParam.getJobSystem());
        jobInfo.setJobExecuteRule(jobInfoSaveParam.getJobExecuteRule());
        jobInfo.setJobExecuteType(jobInfoSaveParam.getJobExecuteType());
        jobInfo.setJobName(jobInfoSaveParam.getJobName());
        jobInfo.setJobStartTime(DateUtil.parseDateTime(jobInfoSaveParam.getJobStartTime()));
        jobInfo.setJobEndTime(DateUtil.parseDateTime(jobInfoSaveParam.getJobEndTime()));
        jobInfo.setJobRetryTimes(jobInfoSaveParam.getJobRetryTimes());
        jobInfo.setJobNotifySucc(jobInfoSaveParam.getJobNotifySucc());
        return jobInfo;
    }
    private void saveJobLinkInfo(JobInfo jobInfo, JobInfoSaveParam jobInfoSaveParam, UserAccount userAccount){
        JobLinkInfo record = new JobLinkInfo();
        record.setJobId(jobInfo.getJobId());
        record.setIsDel(IsType.NO.getValue());
        List<JobLinkInfo> jobLinkInfoList = jobLinkInfoMapper.selectByJobLinkInfo(record);
        for (int i = 0; jobLinkInfoList!=null && i < jobLinkInfoList.size(); i++) {
            JobLinkInfo jobLinkInfo = jobLinkInfoList.get(i);
            jobLinkInfo.setIsDel(IsType.YES.getValue());
            jobLinkInfo.setUpdateId(userAccount.getUserId()+"");
            jobLinkInfo.setUpdateTime(DateUtil.getCurrentDate());
            jobLinkInfoMapper.updateByPrimaryKeySelective(jobLinkInfo);
        }
        String[] jobLinkArr = jobInfoSaveParam.getJobLinkListStr().split(";");
        for (int i = 0; i < jobLinkArr.length; i++) {
            String[] jobLink = jobLinkArr[i].split("\\|");
            JobLinkInfo jobLinkInfo = new JobLinkInfo();
            jobLinkInfo.setIsValid(IsType.YES.getValue());
            jobLinkInfo.setIsDel(IsType.NO.getValue());
            jobLinkInfo.setJobLink(jobLink[0]);
            jobLinkInfo.setServiceName(jobLink[1]);
            jobLinkInfo.setJobId(jobInfo.getJobId());
            jobLinkInfo.setCreateId(userAccount.getUserId()+"");
            jobLinkInfo.setCreateTime(DateUtil.getCurrentDate());
            jobLinkInfo.setUpdateId(userAccount.getUserId()+"");
            jobLinkInfo.setUpdateTime(DateUtil.getCurrentDate());
            jobLinkInfoMapper.insertSelective(jobLinkInfo);
        }
    }


}
