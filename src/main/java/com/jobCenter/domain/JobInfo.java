package com.jobCenter.domain;

import java.util.Date;
/**
 * 描述：定时任务主信息
 * 作者 ：kangzz
 * 日期 ：2016-03-19 21:57:09
 */
public class JobInfo {

    private Integer jobId;//定时任务信息主键

    private String jobName;//定时任务名称

    private Integer jobExecuteType;//'定时任务执行类型。0 只执行一台 1 全部执行'

    private String jobExecuteRule;//'执行规则'

    private Integer jobNotifySucc;//'是否需要通知成功 1 是 0 否'

    private Integer jobRetryTimes;//定时任务重试次数

    private Date jobStartTime;//'任务生效时间'

    private Date jobEndTime;//'任务失效时间'

    private Integer isValid;//'是否生效 1是 0 否'

    private Integer isDel;//'是否删除 1 是 0 否'

    private String createId;//'创建人'

    private Date createTime;//'创建时间'

    private String updateId;//'修改人'

    private Date updateTime;//'修改时间'

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId == null ? null : jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
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
        this.jobExecuteRule = jobExecuteRule == null ? null : jobExecuteRule.trim();
    }

    public Integer getJobNotifySucc() {
        return jobNotifySucc;
    }

    public void setJobNotifySucc(Integer jobNotifySucc) {
        this.jobNotifySucc = jobNotifySucc;
    }

    public Date getJobStartTime() {
        return jobStartTime;
    }

    public void setJobStartTime(Date jobStartTime) {
        this.jobStartTime = jobStartTime;
    }

    public Date getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(Date jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getJobRetryTimes() {
        return jobRetryTimes;
    }

    public void setJobRetryTimes(Integer jobRetryTimes) {
        this.jobRetryTimes = jobRetryTimes;
    }
}