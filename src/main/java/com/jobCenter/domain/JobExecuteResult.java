package com.jobCenter.domain;

import java.util.Date;
/**
 * 描述：任务执行记录实体
 * 作者 ：kangzz
 * 日期 ：2016-03-22 22:28:19
 */
public class JobExecuteResult {
    private Long id;//执行结果id

    private String jobUuid;//某条任务标志

    private String jobId;//定时任务主表ID

    private String jobLinkId;//定时任务子任务id

    private Date jobStartTime;//定时任务开始时间

    private Date jobEndTime;//定时任务回调时间

    private String resultStatus;//执行结果标志

    private Integer resultCode;//执行结果编码

    private String resultMessage;//执行结果信息

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobUuid() {
        return jobUuid;
    }

    public void setJobUuid(String jobUuid) {
        this.jobUuid = jobUuid == null ? null : jobUuid.trim();
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getJobLinkId() {
        return jobLinkId;
    }

    public void setJobLinkId(String jobLinkId) {
        this.jobLinkId = jobLinkId == null ? null : jobLinkId.trim();
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus == null ? null : resultStatus.trim();
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage == null ? null : resultMessage.trim();
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
}