package com.jobCenter.service.impl;

import com.jobCenter.domain.HeartBeatInfo;
import com.jobCenter.domain.JobExecuteResult;
import com.jobCenter.domain.JobInfo;
import com.jobCenter.domain.JobLinkInfo;
import com.jobCenter.enums.IsType;
import com.jobCenter.job.QuartzJob;
import com.jobCenter.job.QuartzManager;
import com.jobCenter.mapper.HeartBeatInfoMapper;
import com.jobCenter.mapper.JobExecuteResultMapper;
import com.jobCenter.mapper.JobInfoMapper;
import com.jobCenter.mapper.JobLinkInfoMapper;
import com.jobCenter.model.JobInfoModel;
import com.jobCenter.model.JobLinkInfoModel;
import com.jobCenter.service.IJobService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "jobService")
@Transactional
public class JobServiceImpl implements IJobService {
    @Autowired
    private JobInfoMapper jobInfoMapper;
    @Autowired
    private JobLinkInfoMapper jobLinkInfoMapper;
    @Autowired
    private HeartBeatInfoMapper heartBeatInfoMapper;
    @Autowired
    private JobExecuteResultMapper jobExecuteResultMapper;

    private final static Logger logger = Logger.getLogger(JobServiceImpl.class);

    /**
     * 描述： 初始化如无数据需要加载 初始化心跳数据
     * 作者 ：kangzz
     * 日期 ：2016-03-19 01:30:16
     */
    public void initHeartBeatInfo(HeartBeatInfo heartBeatInfo) {
        HeartBeatInfo searchParam = new HeartBeatInfo();
        searchParam.setHeartType(heartBeatInfo.getHeartType());
        searchParam.setIsDel(IsType.NO.getValue());
        List<HeartBeatInfo> infoList = heartBeatInfoMapper.selectByRecord(searchParam);
        if (infoList == null || infoList.isEmpty()) {
            heartBeatInfoMapper.insertSelective(heartBeatInfo);
        }
    }

    /**
     * 描述：检查当前机器是否是主机 通过根据主机标志更新数据条数来判断
     * 作者 ：kangzz
     * 日期 ：2016-03-18 23:25:16
     */
    public Boolean cheakIsMasterAndUpdateHeartBeat(HeartBeatInfo heartBeatInfo) {

        heartBeatInfo.setIsDel(IsType.NO.getValue());
        int countNum = heartBeatInfoMapper.updateByMasterIdentity(heartBeatInfo);
        if (countNum > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 描述：切换当前机器为主机是否成功
     * 作者 ：kangzz
     * 日期 ：2016-03-18 23:25:49
     */
    public Boolean changeToMaster(HeartBeatInfo heartBeatInfo) {
        int countNum = heartBeatInfoMapper.updateByOutTime(heartBeatInfo);
        if (countNum > 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 描述：加载所有的定时任务到内存中
     * 作者 ：kangzz
     * 日期 ：2016-03-19 02:11:42
     */
    public Boolean loadAllJobListForMaster() {

        try {
            //获取所有的主任务信息
            List<JobInfoModel> jobList = getAllJobInfo();
            int jobSize = jobList.size();

            for (int i = 0; i < jobSize; i++) {
                JobInfoModel jobInfoMode = jobList.get(i);
                //获取所有的主任务下子任务信息 如果为空 不用加载到任务列表中
                List<JobLinkInfoModel> linkList = jobInfoMode.getJobLinkInfoModels();
                if (linkList == null || linkList.isEmpty()) {
                    continue;
                }
                QuartzJob quartzJob = new QuartzJob();
                //添加任务信息
                QuartzManager.addJob(jobInfoMode.getJobName(), quartzJob.getClass(), jobInfoMode.getJobExecuteRule(), jobInfoMode);
            }
            return true;
        } catch (Exception e) {
            logger.error("添加所有的任务信息异常:", e);
            return false;
        }

    }

    /**
     * 描述：获取所有的任务信息
     * 作者 ：kangzz
     * 日期 ：2016-03-18 00:48:44
     */
    public List<JobInfoModel> getAllJobInfo() {

        List<JobInfoModel> jobInfoModes = new ArrayList<JobInfoModel>();

        try {

            logger.info("查询全部定时任务信息开始!");
            Long startTime = System.currentTimeMillis();
            //封装查询所有定时任务数据条件
            JobInfo jobInfo = new JobInfo();
            jobInfo.setIsDel(IsType.NO.getValue());
            jobInfo.setIsValid(IsType.YES.getValue());
            //查询所有定时任务数据
            List<JobInfo> jobInfolList = jobInfoMapper.selectByJobInfo(jobInfo);
            //遍历所有任务数据 进而查询每个任务下的链接请求信息
            if (jobInfolList != null && !jobInfolList.isEmpty()) {
                int size = jobInfolList.size();
                JobInfo jobInfoDb = null;
                //定义查询任务下请求信息的条件
                JobLinkInfo searchJobLinkInfo = new JobLinkInfo();
                for (int i = 0; i < size; i++) {
                    //最终封装返回的数据
                    JobInfoModel jobInfoModel = new JobInfoModel();
                    //根据主任务id查询主任务下子任务信息 如果为空 则不需要加载到任务列表中
                    jobInfoDb = jobInfolList.get(i);
                    searchJobLinkInfo.setJobId(jobInfoDb.getJobId());
                    searchJobLinkInfo.setIsDel(IsType.NO.getValue());
                    searchJobLinkInfo.setIsValid(IsType.YES.getValue());
                    List<JobLinkInfo> jobLinkInfoList =
                            jobLinkInfoMapper.selectByJobLinkInfo(searchJobLinkInfo);
                    if (jobLinkInfoList == null || jobLinkInfoList.isEmpty()) {
                        continue;
                    }
                    int linkSize = jobLinkInfoList.size();
                    JobLinkInfo jobLinkInfoDb = null;
                    List<JobLinkInfoModel> jobLinkInfoModels = new ArrayList<JobLinkInfoModel>();
                    for (int j = 0; j < linkSize; j++) {
                        JobLinkInfoModel jobLinkModel = new JobLinkInfoModel();
                        jobLinkInfoDb = jobLinkInfoList.get(j);
                        jobLinkModel.setJobId(jobLinkInfoDb.getJobId());
                        jobLinkModel.setJobLink(jobLinkInfoDb.getJobLink());
                        jobLinkModel.setJobLinkId(jobLinkInfoDb.getJobLinkId());
                        jobLinkModel.setServiceName(jobLinkInfoDb.getServiceName());
                        jobLinkInfoModels.add(jobLinkModel);
                    }
                    jobInfoModel.setJobLinkInfoModels(jobLinkInfoModels);
                    jobInfoModel.setJobName(jobInfoDb.getJobName());
                    jobInfoModel.setJobId(jobInfoDb.getJobId());
                    jobInfoModel.setJobExecuteRule(jobInfoDb.getJobExecuteRule());
                    jobInfoModel.setJobExecuteType(jobInfoDb.getJobExecuteType());
                    jobInfoModel.setJobNotifySucc(jobInfoDb.getJobNotifySucc());
                    jobInfoModel.setJobRetryTimes(jobInfoDb.getJobRetryTimes());
                    jobInfoModel.setJobStartTime(jobInfoDb.getJobStartTime());
                    jobInfoModel.setJobEndTime(jobInfoDb.getJobEndTime());
                    jobInfoModes.add(jobInfoModel);

                }
            }
            Long endTime = System.currentTimeMillis();
            logger.info("查询全部定时任务信息结束!");
            logger.info("耗时:" + (endTime - startTime));
        } catch (Exception e) {
            logger.error("查询任务集合信息异常!", e);
        }
        logger.info("添加任务总条数["+jobInfoModes.size()+"]条");
        return jobInfoModes;
    }

    /**
     * 描述：移除所有的定时任务信息
     * 作者 ：kangzz
     * 日期 ：2016-03-22 09:42:26
     */
    public Boolean removeAllJobs(){
        try {
            QuartzManager.removeAllJobs();
            return true;
        }catch (Exception e) {
            logger.error("移除所有的定时任务信息异常!", e);
            return false;
        }
    }
    /**
     * 描述：保存任务执行日志
     * 作者 ：kangzz
     * 日期 ：2016-03-22 22:13:49
     */
    public synchronized void saveJobExecuteResult(JobExecuteResult record){
        try {
            jobExecuteResultMapper.insertSelective(record);
        }catch (Exception e) {
            logger.error("初始保存任务执行信息异常!", e);
        }
    }
    /**
     * 描述：更新任务执行日志
     * 作者 ：kangzz
     * 日期 ：2016-03-22 22:27:56
     */
    public synchronized void updateJobExecuteResultByUuid(JobExecuteResult record){
        try {
            if(jobExecuteResultMapper.updateByUuid(record) == 0){
                throw new RuntimeException("更新执行数据失败!没有找到纪录!");
            }
        }catch (Exception e){
            logger.error("更新定时任务执行数据失败!",e);
        }
    }
}
