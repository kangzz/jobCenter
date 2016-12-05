package com.jobCenter.service.impl;

import com.jobCenter.comm.CommonException;
import com.jobCenter.comm.SystemConstant;
import com.jobCenter.domain.HeartBeatInfo;
import com.jobCenter.domain.JobInfo;
import com.jobCenter.domain.JobLinkInfo;
import com.jobCenter.enums.IsType;
import com.jobCenter.job.QuartzJob;
import com.jobCenter.job.QuartzManager;
import com.jobCenter.mapper.JobExecuteResultMapper;
import com.jobCenter.mapper.JobInfoMapper;
import com.jobCenter.mapper.JobLinkInfoMapper;
import com.jobCenter.model.JobInfoModel;
import com.jobCenter.model.JobLinkInfoModel;
import com.jobCenter.model.authority.logon.UserAccount;
import com.jobCenter.model.dto.JobExecuteResultDto;
import com.jobCenter.model.dto.JobInfoDto;
import com.jobCenter.model.param.JobExecuteResultParam;
import com.jobCenter.model.param.JobInfoSaveParam;
import com.jobCenter.model.param.JobInfoSearchParam;
import com.jobCenter.service.JobInfoService;
import com.jobCenter.service.JobService;
import com.jobCenter.util.DateUtil;
import com.jobCenter.util.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service(value = "jobInfoService")
@Transactional
public class JobInfoServiceImpl implements JobInfoService {
    @Autowired
    private JobInfoMapper jobInfoMapper;
    @Autowired
    private JobLinkInfoMapper jobLinkInfoMapper;
    @Autowired
    private JobExecuteResultMapper jobExecuteResultMapper;
    @Autowired
    private JobService jobService;

    private final static Logger logger = Logger.getLogger(JobInfoServiceImpl.class);
    /**
     * 描述：查询定时任务列表
     * 作者 ：kangzz
     * 日期 ：2016-11-28 21:28:34
     */
    public Map<String,Object> queryJobListByJobInfoSearchParam(JobInfoSearchParam jobInfoSearchParam){
        List<JobInfoDto> list = jobInfoMapper.queryJobListByJobInfoSearchParam(jobInfoSearchParam);
        if(jobService.checkIsMasterAndUpdateHeartBeat()){
            for (int i = 0; list != null && i < list.size(); i++) {
                try {
                    BeanUtils.copyProperties(list.get(i), QuartzManager.getJobExecuteModel(list.get(i).getJobName()));
                }catch (Exception e){

                }
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        long totalCount = jobInfoMapper.queryJobCountByJobInfoSearchParam(jobInfoSearchParam);;
        result.put("rows", list);
        result.put("total", totalCount);
        return result;
    }
    /**
     * 描述： 保存任务数据
     * 作者 ：kangzz
     * 日期 ：2016-12-01 16:53:19
     */
    public void saveJobInfo(JobInfoSaveParam jobInfoSaveParam, UserAccount userAccount){
        this.checkIsMaster();
        JobInfo jobInfo = this.getJobInfoForSave(jobInfoSaveParam, userAccount);
        if(jobInfo.getJobId() == null){
            jobInfoMapper.insertSelective(jobInfo);
        }else{
            jobInfoMapper.updateByPrimaryKeySelective(jobInfo);
        }
        List<JobLinkInfo> jobLinkInfoList = this.saveJobLinkInfo(jobInfo,jobInfoSaveParam, userAccount);
        //获取定时任务实体
        JobInfoModel jobInfoMode = this.changeJobAndLinkToModel(jobInfo,jobLinkInfoList);
        //先移除现有的定时任务 再添加定时任务
        QuartzManager.removeJob(jobInfoMode.getJobName());
        if(IsType.YES.getValue() == jobInfo.getIsValid()){
            QuartzJob quartzJob = new QuartzJob();
            QuartzManager.addJob(jobInfoMode.getJobName(), quartzJob.getClass(), jobInfoMode.getJobExecuteRule(), jobInfoMode);
        }
    }
    /**
     * 描述：封装加载到任务中的实体数据
     * 作者 ：kangzz
     * 日期 ：2016-12-05 23:33:55
     */
    private JobInfoModel changeJobAndLinkToModel(JobInfo jobInfo, List<JobLinkInfo> jobLinkInfoList){
        JobInfoModel model = new JobInfoModel();
        try{
            BeanUtils.copyProperties(model,jobInfo);
            List<JobLinkInfoModel> jobLinkInfoModels = new ArrayList<JobLinkInfoModel>();
            for (int i = 0; jobLinkInfoList!=null && i < jobLinkInfoList.size(); i++) {
                JobLinkInfoModel linkModel = new JobLinkInfoModel();
                JobLinkInfo jobLinkInfo = jobLinkInfoList.get(i);
                BeanUtils.copyProperties(linkModel,jobLinkInfo);
                jobLinkInfoModels.add(linkModel);
            }
            model.setJobLinkInfoModels(jobLinkInfoModels);
        }catch (Exception e){
            logger.error("",e);
        }
        return model;
    }
    //封装定时任务主表信息数据
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
    /**
     * 描述：保存任务对应的调用接口信息列表
     * 作者 ：kangzz
     * 日期 ：2016-12-01 16:49:31
     */
    private List<JobLinkInfo> saveJobLinkInfo(JobInfo jobInfo, JobInfoSaveParam jobInfoSaveParam, UserAccount userAccount){
        //查询本次操作的任务是有已配置调用列表
        JobLinkInfo record = new JobLinkInfo();
        record.setJobId(jobInfo.getJobId());
        List<JobLinkInfo> dbJobLinkInfoList = jobLinkInfoMapper.selectByJobLinkInfo(record);
        //将本次保存的数据转换成任务对应列表数据
        List<JobLinkInfo> thisJobLinkList = this.changeJobLinkStrToList(jobInfoSaveParam.getJobLinkListStr());
        //如果之前没有过 那么直接保存插入数据
        if(dbJobLinkInfoList == null || dbJobLinkInfoList.isEmpty()){
            return this.saveInsertJobLinkInfo(thisJobLinkList,jobInfo,userAccount);
        }else{
            //拆分数据 如果已经存在并且再用 那么不做操作; 如果不存在 那么插入; 如果有移除 那么修改数据库数据
            return this.updateJobLinkInfo(thisJobLinkList,dbJobLinkInfoList,jobInfo,userAccount);
        }
    }
    /**
     * 描述：已配置过调用列表的处理
     * 作者 ：kangzz
     * 日期 ：2016-12-01 16:51:44
     */
    private List<JobLinkInfo> updateJobLinkInfo(List<JobLinkInfo> thisJobLinkList, List<JobLinkInfo> dbJobLinkInfoList, JobInfo jobInfo,UserAccount userAccount){
        //需要新增的link数据
        List<JobLinkInfo> needInsertList = new ArrayList<JobLinkInfo>();
        //需要恢复的link数据
        List<JobLinkInfo> needReviveList = new ArrayList<JobLinkInfo>();
        //需要移除的link数据
        List<JobLinkInfo> needDeleteList = new ArrayList<JobLinkInfo>();
        //需要返回的有效link数据
        List<JobLinkInfo> returnList = new ArrayList<JobLinkInfo>();
        for (int i = 0; i < thisJobLinkList.size(); i++) {
            //获取本次需要新增的任务机器列表数据
            JobLinkInfo thisModel = thisJobLinkList.get(i);
            for (int j = 0; j < dbJobLinkInfoList.size(); j++) {
                JobLinkInfo dbModel = thisJobLinkList.get(j);
                //如果这个数据再数据库中存在 需要判断一下是否已删除 如果删除状态 需要重新启用
                if(thisModel.getJobLink().equals(dbModel.getJobLink())
                        && thisModel.getServiceName().equals(dbModel.getServiceName())){
                    if(dbModel.getIsDel() == IsType.YES.getValue() || dbModel.getIsValid() == IsType.NO.getValue()){
                        needReviveList.add(dbModel);
                    }
                }else{
                    needInsertList.add(thisModel);
                }
            }
        }
        for (int i = 0; i < dbJobLinkInfoList.size(); i++) {
            JobLinkInfo dbModel = thisJobLinkList.get(i);
            for (int j = 0; j < thisJobLinkList.size(); j++) {
                JobLinkInfo thisModel = thisJobLinkList.get(j);
                if(!thisModel.getJobLink().equals(dbModel.getJobLink())
                        && !thisModel.getServiceName().equals(dbModel.getServiceName())){
                    needDeleteList.add(dbModel);
                }
            }
        }
        List<JobLinkInfo> successSaveList = this.saveInsertJobLinkInfo(needInsertList,jobInfo,userAccount);
        if(successSaveList!=null && !successSaveList.isEmpty()){
            returnList.addAll(successSaveList);
        }
        List<JobLinkInfo> successReviveList = this.reviveJobLinkInfoList(needReviveList,userAccount);
        if(successReviveList!=null && !successReviveList.isEmpty()){
            returnList.addAll(successReviveList);
        }
        this.deleteDbJobLinkInfo(needDeleteList,userAccount);
        return returnList;

    }
    //将本次配置信息转换成List
    private List<JobLinkInfo> changeJobLinkStrToList(String jobLinkListStr){
        List<JobLinkInfo> list = new ArrayList<JobLinkInfo>();
        try{
            String[] jobLinkArr = jobLinkListStr.split(";");
            for (int i = 0; i < jobLinkArr.length; i++) {
                String[] jobLink = jobLinkArr[i].split("\\|");
                JobLinkInfo jobLinkInfo = new JobLinkInfo();
                String jobLinkStr = jobLink[0];
                String serviceName = jobLink[1];
                if(!StringUtil.isBlank(jobLinkStr) && !StringUtil.isBlank(serviceName)){
                    jobLinkInfo.setJobLink(jobLinkStr);
                    jobLinkInfo.setServiceName(serviceName);
                    list.add(jobLinkInfo);
                }
            }
        }catch (Exception e){
            throw new CommonException(1000);
        }
        return list;
    }
    //保存调用列表信息
    private List<JobLinkInfo> saveInsertJobLinkInfo(List<JobLinkInfo> jobLinkInfoList, JobInfo jobInfo, UserAccount userAccount){
        if(jobLinkInfoList != null && !jobLinkInfoList.isEmpty()){
            for (int i = 0; i < jobLinkInfoList.size(); i++) {
                JobLinkInfo jobLinkInfo = jobLinkInfoList.get(i);
                jobLinkInfo.setIsValid(IsType.YES.getValue());
                jobLinkInfo.setIsDel(IsType.NO.getValue());
                jobLinkInfo.setJobId(jobInfo.getJobId());
                jobLinkInfo.setCreateId(userAccount.getUserId()+"");
                jobLinkInfo.setCreateTime(DateUtil.getCurrentDate());
                jobLinkInfo.setUpdateId(userAccount.getUserId()+"");
                jobLinkInfo.setUpdateTime(DateUtil.getCurrentDate());
                jobLinkInfoMapper.insertSelective(jobLinkInfo);
            }
        }
        return jobLinkInfoList;
    }
    //复活以前可能作废的信息
    private List<JobLinkInfo> reviveJobLinkInfoList(List<JobLinkInfo> jobLinkInfoList, UserAccount userAccount){
        for (int i = 0; jobLinkInfoList!=null && i < jobLinkInfoList.size(); i++) {
            JobLinkInfo jobLinkInfo = jobLinkInfoList.get(i);
            jobLinkInfo.setIsDel(IsType.NO.getValue());
            jobLinkInfo.setIsValid(IsType.YES.getValue());
            jobLinkInfo.setUpdateId(userAccount.getUserId()+"");
            jobLinkInfo.setUpdateTime(DateUtil.getCurrentDate());
            jobLinkInfoMapper.updateByPrimaryKeySelective(jobLinkInfo);
        }
        return jobLinkInfoList;
    }
    //移除本次删除的机器信息
    private void deleteDbJobLinkInfo(List<JobLinkInfo> jobLinkInfoList, UserAccount userAccount){
        for (int i = 0; jobLinkInfoList!=null && i < jobLinkInfoList.size(); i++) {
            JobLinkInfo jobLinkInfo = jobLinkInfoList.get(i);
            jobLinkInfo.setIsDel(IsType.YES.getValue());
            jobLinkInfo.setUpdateId(userAccount.getUserId()+"");
            jobLinkInfo.setUpdateTime(DateUtil.getCurrentDate());
            jobLinkInfoMapper.updateByPrimaryKeySelective(jobLinkInfo);
        }
    }

    public void deleteJobInfoById(String jobId,UserAccount userAccount){
        this.checkIsMaster();
        JobInfo jobInfo = jobInfoMapper.selectByPrimaryKey(jobId);
        if(jobInfo != null){
            jobInfo.setIsDel(IsType.YES.getValue());
            jobInfo.setUpdateTime(DateUtil.getCurrentDate());
            jobInfo.setUpdateId(userAccount.getUserId()+"");
            jobInfoMapper.updateByPrimaryKeySelective(jobInfo);
            JobLinkInfo record = new JobLinkInfo();
            record.setJobId(Integer.valueOf(jobId));
            record.setIsDel(IsType.NO.getValue());
            List<JobLinkInfo> jobLinkList = jobLinkInfoMapper.selectByJobLinkInfo(record);
            this.deleteDbJobLinkInfo(jobLinkList,userAccount);
            QuartzManager.removeJob(jobInfo.getJobName());
        }
    }
    public void changeJobValidById(String jobId, Integer isValid, UserAccount userAccount){
        this.checkIsMaster();
        JobInfo jobInfo = jobInfoMapper.selectByPrimaryKey(jobId);
        if(jobInfo != null){
            jobInfo.setIsValid(isValid);
            jobInfo.setUpdateTime(DateUtil.getCurrentDate());
            jobInfo.setUpdateId(userAccount.getUserId()+"");
            jobInfoMapper.updateByPrimaryKeySelective(jobInfo);
            if(isValid == IsType.YES.getValue()){
                //根据定时任务主体获取定时任务执行model
                JobInfoModel jobInfoMode = jobService.getJobModel(jobInfo);
                //先移除现有的定时任务 再添加定时任务
                QuartzManager.removeJob(jobInfoMode.getJobName());
                QuartzJob quartzJob = new QuartzJob();
                QuartzManager.addJob(jobInfoMode.getJobName(), quartzJob.getClass(), jobInfoMode.getJobExecuteRule(), jobInfoMode);
            }else{
                QuartzManager.removeJob(jobInfo.getJobName());
            }
        }
    }
    public JobInfoSaveParam getJobInfoToEdit(String jobId){
        JobInfoSaveParam jobInfoSaveParam = new JobInfoSaveParam();
        JobInfo jobInfo = jobInfoMapper.selectByPrimaryKey(jobId);
        jobInfoSaveParam.setJobId(jobInfo.getJobId());
        jobInfoSaveParam.setJobName(jobInfo.getJobName());
        jobInfoSaveParam.setJobSystem(jobInfo.getJobSystem());
        jobInfoSaveParam.setJobExecuteType(jobInfo.getJobExecuteType());
        jobInfoSaveParam.setJobExecuteRule(jobInfo.getJobExecuteRule());
        jobInfoSaveParam.setJobNotifySucc(jobInfo.getJobNotifySucc());
        jobInfoSaveParam.setJobRetryTimes(jobInfo.getJobRetryTimes());
        jobInfoSaveParam.setJobStartTime(DateUtil.formatDate(jobInfo.getJobStartTime(),DateUtil.DATETIME24_PATTERN_LINE));
        jobInfoSaveParam.setJobEndTime(DateUtil.formatDate(jobInfo.getJobEndTime(),DateUtil.DATETIME24_PATTERN_LINE));
        jobInfoSaveParam.setIsValid(jobInfo.getIsValid());
        StringBuffer jobLinkListStr = new StringBuffer();
        JobLinkInfo record = new JobLinkInfo();
        record.setJobId(Integer.valueOf(jobId));
        record.setIsDel(IsType.NO.getValue());
        record.setIsValid(IsType.YES.getValue());
        List<JobLinkInfo> jobLinkList = jobLinkInfoMapper.selectByJobLinkInfo(record);
        for (int i = 0; i < jobLinkList.size(); i++) {
            jobLinkListStr.append(jobLinkList.get(i).getJobLink());
            jobLinkListStr.append("|");
            jobLinkListStr.append(jobLinkList.get(i).getServiceName());
            jobLinkListStr.append(";");
        }
        jobInfoSaveParam.setJobLinkListStr(jobLinkListStr.toString());
        return jobInfoSaveParam;
    }
    public List<JobInfo> queryJobInfoList(JobInfo jobInfo){
        return jobInfoMapper.selectByJobInfo(jobInfo);
    }
    public Map<String,Object> queryJobExecuteListSearchParam(JobExecuteResultParam jobExecuteResultParam){
        List<JobExecuteResultDto> list = jobExecuteResultMapper.queryJobExecuteListSearchParam(jobExecuteResultParam);
        Map<String, Object> result = new HashMap<String, Object>();
        long totalCount = jobExecuteResultMapper.queryCountJobExecuteListSearchParam(jobExecuteResultParam);;
        result.put("rows", list);
        result.put("total", totalCount);
        return result;
    }
    /**
     * 描述：校验当前机器是否为主机 非主机无法执行新增 修改 作废定时任务操作
     * 作者 ：kangzz
     * 日期 ：2016-12-02 20:31:13
     */
    private void checkIsMaster(){
        //校验当前机器是否为主机
        //检查本机是否为主机 同时更新心跳时间
        Boolean isMaster = false;
        try {
            isMaster = jobService.checkIsMasterAndUpdateHeartBeat();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if(!isMaster){
            throw new CommonException(1001);
        }
    }


}
