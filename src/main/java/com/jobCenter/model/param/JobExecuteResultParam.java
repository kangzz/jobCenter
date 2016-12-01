package com.jobCenter.model.param;

import com.jobCenter.util.DateUtil;
import com.jobCenter.util.StringUtil;

/**
 * 描述：任务执行记录实体
 * 作者 ：kangzz
 * 日期 ：2016-03-22 22:28:19
 */
public class JobExecuteResultParam extends PageParam{
    private Long id;//执行结果id
    private String jobUuid;//某条任务标志
    private String jobId;//定时任务主表ID
    private String jobLinkId;//定时任务子任务id
    private String jobStartTimeBegin;//定时任务开始时间
    private String jobStartTimeEnd;//定时任务开始时间
    private String jobEndTimeBegin;//定时任务回调时间
    private String jobEndTimeEnd;//定时任务回调时间
    private String resultStatus;//执行结果标志

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
        if(StringUtil.isBlank(jobUuid)){
            jobUuid = null;
        }
        this.jobUuid = jobUuid;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        if(StringUtil.isBlank(jobId)){
            jobId = null;
        }
        this.jobId = jobId;
    }

    public String getJobLinkId() {
        return jobLinkId;
    }

    public void setJobLinkId(String jobLinkId) {
        if(StringUtil.isBlank(jobLinkId)){
            jobLinkId = null;
        }
        this.jobLinkId = jobLinkId;
    }

    public String getJobStartTimeBegin() {
        return jobStartTimeBegin;
    }

    public void setJobStartTimeBegin(String jobStartTimeBegin) {
        if(StringUtil.isBlank(jobStartTimeBegin)){
            jobStartTimeBegin = null;
        }
        this.jobStartTimeBegin = jobStartTimeBegin;
    }

    public String getJobStartTimeEnd() {
        return jobStartTimeEnd;
    }

    public void setJobStartTimeEnd(String jobStartTimeEnd) {
        if(!StringUtil.isBlank(jobStartTimeEnd)){
            jobStartTimeEnd = DateUtil.formatDate(DateUtil.addDays(DateUtil.parseDate(jobStartTimeEnd),1),DateUtil.DATE_PATTERN_LINE);
        }else{
            jobStartTimeEnd = null;
        }
        this.jobStartTimeEnd = jobStartTimeEnd;
    }

    public String getJobEndTimeBegin() {
        return jobEndTimeBegin;
    }

    public void setJobEndTimeBegin(String jobEndTimeBegin) {
        if(StringUtil.isBlank(jobEndTimeBegin)){
            jobEndTimeBegin = null;
        }
        this.jobEndTimeBegin = jobEndTimeBegin;
    }

    public String getJobEndTimeEnd() {
        return jobEndTimeEnd;
    }

    public void setJobEndTimeEnd(String jobEndTimeEnd) {
        if(!StringUtil.isBlank(jobEndTimeEnd)){
            jobEndTimeEnd = DateUtil.formatDate(DateUtil.addDays(DateUtil.parseDate(jobEndTimeEnd),1),DateUtil.DATE_PATTERN_LINE);
        }else{
            jobEndTimeEnd = null;
        }
        this.jobEndTimeEnd = jobEndTimeEnd;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        if(StringUtil.isBlank(resultStatus)){
            resultStatus = null;
        }
        this.resultStatus = resultStatus;
    }
}