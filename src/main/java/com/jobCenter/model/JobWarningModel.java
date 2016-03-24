package com.jobCenter.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 描述：报警信息实体
 * 作者 ：kangzz
 * 日期 ：2016-03-24 00:09:48
 */
public class JobWarningModel {

    private Long jobId;//报警任务id

    private String warningTitle;//报警标题

    private String warningContent;//报警内容

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getWarningTitle() {
        return warningTitle;
    }

    public void setWarningTitle(String warningTitle) {
        this.warningTitle = warningTitle;
    }

    public String getWarningContent() {
        return warningContent;
    }

    public void setWarningContent(String warningContent) {
        this.warningContent = warningContent;
    }
}