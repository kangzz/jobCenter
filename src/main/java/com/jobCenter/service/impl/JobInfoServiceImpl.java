package com.jobCenter.service.impl;

import com.jobCenter.comm.CommonException;
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
import com.kangzz.mtool.util.BooleanUtils;
import com.kangzz.mtool.util.CollectionUtil;
import com.kangzz.mtool.util.ObjectUtil;
import com.kangzz.mtool.util.StrUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        if(ObjectUtil.isNull(jobInfo.getJobId())){
            jobInfoMapper.insertSelective(jobInfo);
        }else{
            jobInfoMapper.updateByPrimaryKeySelective(jobInfo);
        }
        List<JobLinkInfo> jobLinkInfoList = this.saveJobLinkInfo(jobInfo,jobInfoSaveParam, userAccount);
        //获取定时任务实体
        JobInfoModel jobInfoMode = this.changeJobAndLinkToModel(jobInfo,jobLinkInfoList);
        //先移除现有的定时任务 再添加定时任务
        QuartzManager.removeJob(jobInfoMode.getJobName());
        if(ObjectUtil.equals(IsType.YES.getValue(),jobInfo.getIsValid())){
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
        if(ObjectUtil.isNull(jobId)){
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
        if(ObjectUtil.isNullOrEmpty(dbJobLinkInfoList)){
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
        //将本次新增的数据封装到map中 key是 link+serviceName Value是实体
        Map<String,JobLinkInfo> thisJobLinkMap = new HashMap<String, JobLinkInfo>();
        for (int i = 0; thisJobLinkList != null && i < thisJobLinkList.size(); i++) {
            JobLinkInfo jobLinkInfo = thisJobLinkList.get(i);
            thisJobLinkMap.put(jobLinkInfo.getJobLink()+jobLinkInfo.getServiceName(),jobLinkInfo);
        }
        //将数据库的数据封装到map中 key是 link+serviceName Value是实体
        Map<String,JobLinkInfo> dbJobLinkMap = new HashMap<String, JobLinkInfo>();
        for (int i = 0; dbJobLinkInfoList != null && i < dbJobLinkInfoList.size(); i++) {
            JobLinkInfo jobLinkInfo = dbJobLinkInfoList.get(i);
            dbJobLinkMap.put(jobLinkInfo.getJobLink()+jobLinkInfo.getServiceName(),jobLinkInfo);
        }
        //分发本次数据 是需要复活还是需要新增
        for (int i = 0; thisJobLinkList != null && i < thisJobLinkList.size(); i++) {
            JobLinkInfo jobLinkInfo = thisJobLinkList.get(i);
            JobLinkInfo mapModel = dbJobLinkMap.get(jobLinkInfo.getJobLink()+jobLinkInfo.getServiceName());
            //如果数据库中有这个配置url了
            if(ObjectUtil.isNotNull(mapModel)){
                //如果原来的数据已经作废 那么要恢复启用 否则不需要处理
                if(BooleanUtils.or(mapModel.getIsDel() == IsType.YES.getValue(),mapModel.getIsValid() == IsType.NO.getValue())){
                    needReviveList.add(mapModel);
                }
            }else{//如果数据库中没有当前这个配置 那么需要新增
                needInsertList.add(jobLinkInfo);
            }
        }
        //校验数据库中的记录在本次添加是否存在 如果没有 那么要移除掉
        for (int i = 0; dbJobLinkInfoList != null && i < dbJobLinkInfoList.size(); i++) {
            JobLinkInfo jobLinkInfo = dbJobLinkInfoList.get(i);
            if(!thisJobLinkMap.containsKey(jobLinkInfo.getJobLink()+jobLinkInfo.getServiceName())){
                needDeleteList.add(jobLinkInfo);
            }
        }
        //需要返回的有效link数据
        List<JobLinkInfo> returnList = new ArrayList<JobLinkInfo>();
        List<JobLinkInfo> successSaveList = this.saveInsertJobLinkInfo(needInsertList,jobInfo,userAccount);
        if(CollectionUtil.isNotEmpty(successSaveList)){
            returnList.addAll(successSaveList);
        }
        List<JobLinkInfo> successReviveList = this.reviveJobLinkInfoList(needReviveList,userAccount);
        if(CollectionUtil.isNotEmpty(successReviveList)){
            returnList.addAll(successReviveList);
        }
        this.deleteDbJobLinkInfo(needDeleteList,userAccount);
        return returnList;
    }
    //将本次配置信息转换成List
    private List<JobLinkInfo> changeJobLinkStrToList(String jobLinkListStr){
        List<JobLinkInfo> list = new ArrayList<JobLinkInfo>();
        try{
            String[] jobLinkArr = StrUtil.split(jobLinkListStr,";");
            for (int i = 0; i < jobLinkArr.length; i++) {
                if(StringUtils.isBlank(jobLinkArr[i])){
                    continue;
                }
                String[] jobLink = StrUtil.split(jobLinkArr[i],":");
                JobLinkInfo jobLinkInfo = new JobLinkInfo();
                String serviceName = jobLink[0];
                String jobLinkStr = jobLink[1];
                if(BooleanUtils.andNotNullOrEmpty(jobLinkStr,serviceName)){
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
        if(ObjectUtil.isNotNullOrEmpty(jobLinkInfoList)){
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
        if(ObjectUtil.isNotNull(jobInfo)){
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
        if(ObjectUtil.isNotNull(jobInfo)){
            jobInfo.setIsValid(isValid);
            jobInfo.setUpdateTime(DateUtil.getCurrentDate());
            jobInfo.setUpdateId(userAccount.getUserId()+"");
            jobInfoMapper.updateByPrimaryKeySelective(jobInfo);
            if(ObjectUtil.equals(isValid,IsType.YES.getValue())){
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
            jobLinkListStr.append(jobLinkList.get(i).getServiceName());
            jobLinkListStr.append(":");
            jobLinkListStr.append(jobLinkList.get(i).getJobLink());
            if(i != jobLinkList.size() - 1){
                jobLinkListStr.append(";");
            }
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
