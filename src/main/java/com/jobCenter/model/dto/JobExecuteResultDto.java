package com.jobCenter.model.dto;

import com.jobCenter.enums.DoneStatus;

/**
 * 描述：任务执行记录实体
 * 作者 ：kangzz
 * 日期 ：2016-03-22 22:28:19
 */
public class JobExecuteResultDto {

    private String jobUuid;//某条任务标志
    private String jobName;//任务名称
    private String jobLink;
    private String jobService;
    private String jobStartTime;//定时任务开始时间
    private String jobEndTime;//定时任务回调时间
    private String resultStatus;//执行结果标志
    private String resultMessage;//执行结果信息


    public String getJobUuid() {
        return jobUuid;
    }

    public void setJobUuid(String jobUuid) {
        this.jobUuid = jobUuid;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobLink() {
        return jobLink;
    }

    public void setJobLink(String jobLink) {
        this.jobLink = jobLink;
    }

    public String getJobService() {
        return jobService;
    }

    public void setJobService(String jobService) {
        this.jobService = jobService;
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

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        resultStatus = DoneStatus.lookup.get(resultStatus);
        this.resultStatus = resultStatus;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}