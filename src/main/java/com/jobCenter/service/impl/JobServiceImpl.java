package com.jobCenter.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jobCenter.comm.NeedWarningException;
import com.jobCenter.comm.SystemConstant;
import com.jobCenter.domain.HeartBeatInfo;
import com.jobCenter.domain.JobExecuteResult;
import com.jobCenter.domain.JobInfo;
import com.jobCenter.domain.JobLinkInfo;
import com.jobCenter.enums.*;
import com.jobCenter.job.QuartzJob;
import com.jobCenter.job.QuartzManager;
import com.jobCenter.mapper.*;
import com.jobCenter.model.JobInfoModel;
import com.jobCenter.model.JobLinkInfoModel;
import com.jobCenter.model.JobWarningModel;
import com.jobCenter.model.JobWarningPersonModel;
import com.jobCenter.service.JobService;
import com.jobCenter.util.*;
import com.jobCenter.util.http.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(value = "jobService")
@Transactional
public class JobServiceImpl implements JobService {
    @Autowired
    private JobInfoMapper jobInfoMapper;
    @Autowired
    private JobLinkInfoMapper jobLinkInfoMapper;
    @Autowired
    private HeartBeatInfoMapper heartBeatInfoMapper;
    @Autowired
    private JobExecuteResultMapper jobExecuteResultMapper;
    @Autowired
    private JobWarningPersonInfoMapper jobWarningPersonInfoMapper;

    private final static Logger logger = Logger.getLogger(JobServiceImpl.class);

    /**
     * 描述： 初始化如无数据需要加载 初始化心跳数据
     * 作者 ：kangzz
     * 日期 ：2016-03-19 01:30:16
     */
    public void initHeartBeatInfo(HeartBeatInfo heartBeatInfo) {
        try {
            HeartBeatInfo searchParam = new HeartBeatInfo();
            searchParam.setHeartType(heartBeatInfo.getHeartType());
            searchParam.setIsDel(IsType.NO.getValue());
            List<HeartBeatInfo> infoList = heartBeatInfoMapper.selectByRecord(searchParam);
            if (infoList == null || infoList.isEmpty()) {
                heartBeatInfoMapper.insertSelective(heartBeatInfo);
            }

        } catch (Exception e) {
            throw new NeedWarningException(100, e);
        }
    }

    /**
     * 描述：检查当前机器是否是主机 通过根据主机标志更新数据条数来判断
     * 作者 ：kangzz
     * 日期 ：2016-03-18 23:25:16
     */
    public Boolean checkIsMasterAndUpdateHeartBeat(HeartBeatInfo heartBeatInfo) {
        try {
            heartBeatInfo.setIsDel(IsType.NO.getValue());
            int countNum = heartBeatInfoMapper.updateByMasterIdentity(heartBeatInfo);
            if (countNum > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new NeedWarningException(101, e);
        }

    }

    /**
     * 描述：切换当前机器为主机是否成功
     * 作者 ：kangzz
     * 日期 ：2016-03-18 23:25:49
     */
    public Boolean changeToMaster(HeartBeatInfo heartBeatInfo) {
        try {
            int countNum = heartBeatInfoMapper.updateByOutTime(heartBeatInfo);
            if (countNum > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new NeedWarningException(102, e);
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
            //所有任务的数量
            int jobSize = jobList == null ? 0 : jobList.size();
            //如果任务列表为空 抛出异常
            if(jobSize == 0){
                throw new NeedWarningException(110);
            }
            int failNum = 0;
            for (int i = 0; i < jobSize; i++) {
                JobInfoModel jobInfoMode = jobList.get(i);
                //获取所有的主任务下子任务信息 如果为空 不用加载到任务列表中
                List<JobLinkInfoModel> linkList = jobInfoMode.getJobLinkInfoModels();
                if (linkList == null || linkList.isEmpty()) {
                    continue;
                }
                QuartzJob quartzJob = new QuartzJob();
                //添加任务信息
                try {

                    //这里捕捉是因为不能因为个别任务添加失败影响大批任务添加 导致定时任务系统不可用
                    QuartzManager.addJob(jobInfoMode.getJobName(), quartzJob.getClass(), jobInfoMode.getJobExecuteRule(), jobInfoMode);
                } catch (Exception e) {
                    failNum++;
                    throw new NeedWarningException(103, new String[]{jobInfoMode.getJobName(), String.valueOf(jobInfoMode.getJobId())}, e);
                }
            }
            //如果所有任务都添加失败 那么说明有问题了 要抛出异常
            if (failNum == jobSize) throw new NeedWarningException(104);
            return true;
        } catch (Exception e) {
            throw new NeedWarningException(105, e);
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
            throw new NeedWarningException(106, e);
        }
        logger.info("添加任务总条数[" + jobInfoModes.size() + "]条");
        return jobInfoModes;
    }

    /**
     * 描述：移除所有的定时任务信息
     * 作者 ：kangzz
     * 日期 ：2016-03-22 09:42:26
     */
    public Boolean removeAllJobs() {
        try {
            QuartzManager.removeAllJobs();
            return true;
        } catch (Exception e) {
            throw new NeedWarningException(107, e);
        }
    }

    /**
     * 描述：执行任务 发送任务请求
     * 作者 ：kangzz
     * 日期 ：2016-03-23 00:13:36
     */
    public void sendJobRequest(JobInfoModel jobInfoModel) throws Exception {
        Thread.currentThread().setName("job_center_send_request_job_id:" + jobInfoModel.getJobId());
        Date nowDate = new Date();
        //如果任务不在有效期内 那么不用执行了
        if (!jobInfoModel.getJobStartTime().before(nowDate) || !jobInfoModel.getJobEndTime().after(nowDate)) {
            logger.info("定时任务不在有效期内!");
            return;
        }
        long startTime = System.currentTimeMillis();
        //任务id
        String jobId = String.valueOf(jobInfoModel.getJobId());
        //任务名称
        String jobName = jobInfoModel.getJobName();
        logger.info("任务[" + jobName + "]请求开始时间:" + startTime);
        //0 只执行一台 1 全部执行
        Integer jobExecuteType = jobInfoModel.getJobExecuteType();
        //需要保证通知成功时最大重试次数 这里用的是总调用次数 需要在重试基础上加1
        Integer jobRetryTimes = jobInfoModel.getJobRetryTimes() + 1;
        //通知成功数 当全部通知时 只要有一台没有通知成功 那么就要报警 如果是通知一台 那么只要成功数>0即可
        Integer notifySuccessTimes = 0;
        //提示的报警信息信息
        StringBuffer sb = new StringBuffer();
        //获取主任务下的所有子任务信息
        List<JobLinkInfoModel> jobLinkInfoModels = jobInfoModel.getJobLinkInfoModels();
        int linkModelSize = jobLinkInfoModels != null ? jobLinkInfoModels.size() : 0;
        //遍历所有的子任务链接信息
        for (int i = 0; i < linkModelSize; i++) {
            //获取子任务信息
            JobLinkInfoModel jobLinkInfoModel = jobLinkInfoModels.get(i);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            //获取子任务id
            String jobLinkId = String.valueOf(jobLinkInfoModel.getJobLinkId());
            //封装加密字符串信息 加密字符串使用两个uuid截取部分拼装后再加密
            String uuid = UUID.randomUUID().toString();
            int jobLinkIdSubCount = jobLinkId.length() / SystemConstant.MD5_RATIO;
            jobLinkIdSubCount = jobLinkIdSubCount == 0 ? jobLinkId.length() : jobLinkIdSubCount;
            int uuidSubCount = uuid.length() / SystemConstant.MD5_RATIO;
            uuidSubCount = uuidSubCount == 0 ? uuid.length() : uuidSubCount;
            String securityStr = jobLinkId.substring(0, jobLinkIdSubCount) + uuid.substring(0, uuidSubCount);

            paramMap.put("securityCode", MD5Util.encodeMD5(securityStr, SystemConstant.MD5_KEY));
            paramMap.put("uuid", uuid);//唯一标志本次请求id
            paramMap.put("jobName",jobName);//任务名称
            paramMap.put("jobId", jobId);//任务主id
            paramMap.put("linkId", jobLinkId);//子任务id
            paramMap.put("serviceName", jobLinkInfoModel.getServiceName());//子任务执行的service
            //转换发送参数
            String param = MessageUtil.getParameter(paramMap);
            //获取请求url信息
            String sendUrl = jobLinkInfoModel.getJobLink();

            logger.info("本次定时任务调用地址：" + sendUrl);

            logger.info("本次定时任务调用参数：" + param);

            //本次请求是否成功
            Boolean sendIsSuccess = false;
            String jsonStr = StringUtil.EMPTY;
            String status = "";//通知成功 业务系统返回状态
            JSONObject jsonObj = null;
            int notifyFailTimes = 0;//访问失败次数
            //调用结果数据保存实体
            JobExecuteResult record = new JobExecuteResult();
            record.setJobStartTime(new Date());
            record.setJobId(jobId);
            record.setJobLinkId(jobLinkId);
            record.setJobUuid(uuid);
            for (int j = 0; j < jobRetryTimes; j++) {//遍历循环调用次数
                jsonStr = HttpPoster.postWithRes(sendUrl, param);
                logger.info("任务[" + jobInfoModel.getJobName() + "_jobLinkId:" + jobLinkId + "]第" + (j + 1) + "次调用返回值:" + jsonStr);
                if (checkIsSuccess(jsonStr)) {
                    sendIsSuccess = true;
                    jsonObj = JSONObject.parseObject(jsonStr);
                    status = jsonObj.getString("status");
                    //如果任务不需要执行 那么直接设置结束时间
                    if (DoneStatus.UNDONE.getValue().equalsIgnoreCase(status)) {
                        record.setJobEndTime(new Date());
                    }
                    record.setResultStatus(status);
                    record.setResultCode(jsonObj.getInteger("code"));
                    record.setResultMessage(jsonObj.getString("message"));
                    try {
                        this.saveJobExecuteResult(record);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                    break;
                } else {
                    notifyFailTimes++;
                    continue;
                }
            }
            //如果通知次数等于重试次数 那么调用记录要保存
            if (notifyFailTimes == jobRetryTimes) {
                logger.error("任务[" + jobInfoModel.getJobName() + "_jobLinkId:" + jobLinkId + "]重试" + jobRetryTimes + "次访问后仍未通知成功!");
                record.setResultStatus(DoneStatus.TZSB.getValue());
                record.setResultCode(JobStatus.TZSB.getValue());
                record.setResultMessage(JobStatus.TZSB.getName());
                try {
                    this.saveJobExecuteResult(record);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
            //判断当前调用是否成功 如果成功 成功次数加1 否则拼装失败信息
            if (sendIsSuccess) {
                notifySuccessTimes++;
            } else {
                sb.append("定时任务[" + jobName + "]</br>");
                sb.append("Url:" + sendUrl + "调用" + jobLinkInfoModel.getServiceName() + "[" + jobRetryTimes + "]次失败!</br>");
                sb.append("调用uuid:" + uuid + ";</br>");
                //sb.append("调用参数:"+param+";</br>");
            }
            //如果需要保证通知成功 那么最多要遍历重试次数N次 如果中间有数据返回那么就break
            if (JobExecuteType.ALL.getValue() == jobExecuteType) {
                continue;
            } else if (!sendIsSuccess) {//如果不是全部通知 那么要循环子任务下的其他链接 看是否有可以成功的
                continue;
            } else {
                break;
            }
        }


        //-----------------报警信息开始------------------

        //如果成功数量和链接总数不一致 那么要判断是否为需要全部通知 如果全部通知 数量不一致就需要报警
        //只通知一台 只需要一台成功就不需要报警
        if (notifySuccessTimes != linkModelSize) {
            Boolean needNotify = false;
            if (JobExecuteType.ALL.getValue() == jobExecuteType) {
                needNotify = true;
            } else {
                if (notifySuccessTimes == 0) {
                    needNotify = true;
                }
            }
            if (needNotify) {
                JobWarningModel jobWarningModel = new JobWarningModel();
                jobWarningModel.setJobId(Long.valueOf(jobId));
                jobWarningModel.setWarningTitle("定时任务发送请求异常");
                jobWarningModel.setWarningContent(sb.toString());
                //this.notifyJobCenterManger(jobWarningModel);
                this.notifyJobOwner(jobWarningModel);
            }
        }

        //-----------------报警信息结束-------------------

        long endTime = System.currentTimeMillis();
        logger.info("任务[" + jobInfoModel.getJobName() + "]请求结束时间:" + endTime);

        logger.info("任务[" + jobInfoModel.getJobName() + "]请求总耗时:" + (endTime - startTime) + "毫秒");
    }

    /**
     * 描述：校验调用返回参数是否成功
     * 作者 ：kangzz
     * 日期 ：2016-03-19 21:16:04
     */
    private Boolean checkIsSuccess(String returnJson) {
        if (StringUtil.isBlank(returnJson)) {
            return false;
        } else {
            JSONObject jsonObj = JSONObject.parseObject(returnJson);
            if (!jsonObj.containsKey(StringUtil.STATUS)) {
                return false;
            }
            return true;
        }
    }


    /**
     * 描述：保存任务执行日志
     * 作者 ：kangzz
     * 日期 ：2016-03-22 22:13:49
     */
    public void saveJobExecuteResult(JobExecuteResult record) {
        try {
            jobExecuteResultMapper.insertSelective(record);
        } catch (Exception e) {
            throw new NeedWarningException(108, new String[]{record.getJobUuid()}, e);
        }
    }

    /**
     * 描述：更新任务执行日志
     * 作者 ：kangzz
     * 日期 ：2016-03-22 22:27:56
     */
    public void updateJobExecuteResultByUuid(JobExecuteResult record) {
        try {
            if (jobExecuteResultMapper.updateByUuid(record) == 0) {
                throw new RuntimeException("更新执行数据失败!没有找到纪录!");
            }
        } catch (Exception e) {
            throw new NeedWarningException(109, new String[]{record.getJobUuid()}, e);
        }
    }

    /**
     * 描述：为定时任务系统管理员报警
     * 作者 ：kangzz
     * 日期 ：2016-03-24 22:35:25
     */
    public void notifyJobCenterManger(JobWarningModel jobWarningModel) {
        try {
            JobWarningPersonModel model = new JobWarningPersonModel();
            model.setPersonType(JobWarningPersonType.RWXT.getValue());
            List<JobWarningPersonModel> list = jobWarningPersonInfoMapper.selectJobWarningPerson(model);
            NotifyWarningUtil.notifyJobWarningMessage(list, jobWarningModel);
        } catch (Exception e) {
            logger.error("通知系统管理员失败!!!!", e);
        }
    }

    /**
     * 描述：为具体定时任务负责人报警
     * 作者 ：kangzz
     * 日期 ：2016-03-24 22:35:25
     */
    public void notifyJobOwner(JobWarningModel jobWarningModel) {
        try {
            //根据任务id查询需要发送人员信息
            JobWarningPersonModel model = new JobWarningPersonModel();
            model.setJobId(jobWarningModel.getJobId());
            List<JobWarningPersonModel> list = jobWarningPersonInfoMapper.selectJobWarningPerson(model);
            NotifyWarningUtil.notifyJobWarningMessage(list, jobWarningModel);
        } catch (Exception e) {
            logger.error("通知任务具体负责人失败!!!!", e);
        }
    }

    /**
     * 描述：根据子任务id查询子任务信息
     * 作者 ：kangzz
     * 日期 ：2016-03-25 18:09:30
     */
    public JobLinkInfo getJobLinkInfoById(String linkId){
        return jobLinkInfoMapper.selectByPrimaryKey(linkId);
    }


}
