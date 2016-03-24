package com.jobCenter.model;

/**
 * 描述：报警人员信息
 * 作者 ：kangzz
 * 日期 ：2016-03-23 20:07:20
 */
public class JobWarningPersonModel {

    private Long jobId;//任务id

    private Integer warningType;//报警类型 0 定时任务服务器 1 业务服务器

    private Integer personType;//报警人类型

    private String personName;//报警人姓名

    private String personEmail;//报警人邮箱

    private String personPhone;//报警人手机

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Integer getWarningType() {
        return warningType;
    }

    public void setWarningType(Integer warningType) {
        this.warningType = warningType;
    }

    public Integer getPersonType() {
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    @Override
    public String toString() {
        return "JobWarningPersonModel{" +
                "jobId=" + jobId +
                ", warningType=" + warningType +
                ", personType=" + personType +
                ", personName='" + personName + '\'' +
                ", personEmail='" + personEmail + '\'' +
                ", personPhone='" + personPhone + '\'' +
                '}';
    }
}