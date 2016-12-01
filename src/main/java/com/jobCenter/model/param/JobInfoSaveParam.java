package com.jobCenter.model.param;

/**
 * 描述：定时任务主信息
 * 作者 ：kangzz
 * 日期 ：2016-03-19 21:57:09
 */
public class JobInfoSaveParam{

    private Integer jobId;//定时任务信息主键

    private String jobName;//定时任务名称

    private String jobSystem;//定时任务归属系统

    private Integer jobExecuteType;//'定时任务执行类型。0 只执行一台 1 全部执行'

    private String jobExecuteRule;//'执行规则'

    private Integer jobNotifySucc;//'是否需要通知成功 1 是 0 否'

    private Integer jobRetryTimes;//定时任务重试次数

    private String jobStartTime;//'任务生效时间'

    private String jobEndTime;//'任务失效时间'

    private Integer isValid;//'是否生效 1是 0 否'

    private String jobLinkListStr;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobSystem() {
        return jobSystem;
    }

    public void setJobSystem(String jobSystem) {
        this.jobSystem = jobSystem;
    }

    public Integer getJobExecuteType() {
        return jobExecuteType;
    }

    public void setJobExecuteType(Integer jobExecuteType) {
        this.jobExecuteType = jobExecuteType;
    }

    public String getJobExecuteRule() {
        return jobExecuteRule;
    }

    public void setJobExecuteRule(String jobExecuteRule) {
        this.jobExecuteRule = jobExecuteRule;
    }

    public Integer getJobNotifySucc() {
        return jobNotifySucc;
    }

    public void setJobNotifySucc(Integer jobNotifySucc) {
        this.jobNotifySucc = jobNotifySucc;
    }

    public Integer getJobRetryTimes() {
        return jobRetryTimes;
    }

    public void setJobRetryTimes(Integer jobRetryTimes) {
        this.jobRetryTimes = jobRetryTimes;
    }

    public String getJobStartTime() {
        return jobStartTime;
    }

    public void setJobStartTime(String jobStartTime) {
        this.jobStartTime = jobStartTime;
    }

    public String getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(String jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getJobLinkListStr() {
        return jobLinkListStr;
    }

    public void setJobLinkListStr(String jobLinkListStr) {
        this.jobLinkListStr = jobLinkListStr;
    }
}