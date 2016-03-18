package com.jobCenter.domain;

import java.util.Date;

public class HeartBeatInfo {
    private Integer id;//心跳id

    private String masterIdentity;//心跳主机标志

    private String heartType;//心跳类型

    private Date lastModifyTime;//最后修改时间

    private Integer heartMaxVal;//最大间隔时间

    private Integer isDel;//是否删除

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMasterIdentity() {
        return masterIdentity;
    }

    public void setMasterIdentity(String masterIdentity) {
        this.masterIdentity = masterIdentity == null ? null : masterIdentity.trim();
    }

    public String getHeartType() {
        return heartType;
    }

    public void setHeartType(String heartType) {
        this.heartType = heartType == null ? null : heartType.trim();
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getHeartMaxVal() {
        return heartMaxVal;
    }

    public void setHeartMaxVal(Integer heartMaxVal) {
        this.heartMaxVal = heartMaxVal;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}