package com.jobCenter.model;

/**
 * 描述：报警人员信息
 * 作者 ：kangzz
 * 日期 ：2016-03-23 20:07:20
 */
public class JobWarningPersonModel {

    private Long jobId;//任务id

    private Integer personReceiveType;//报警人类型 主要人员 还是次要人员

    private Integer personType;//人员类型 业务系统 任务系统

    private String personName;//报警人姓名

    private String personEmail;//报警人邮箱

    private String personPhone;//报警人手机

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
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

    public Integer getPersonReceiveType() {
        return personReceiveType;
    }

    public void setPersonReceiveType(Integer personReceiveType) {
        this.personReceiveType = personReceiveType;
    }

    public Integer getPersonType() {
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    @Override
    public String toString() {
        return "JobWarningPersonModel{" +
                "jobId=" + jobId +
                ", personReceiveType=" + personReceiveType +
                ", personType=" + personType +
                ", personName='" + personName + '\'' +
                ", personEmail='" + personEmail + '\'' +
                ", personPhone='" + personPhone + '\'' +
                '}';
    }
}