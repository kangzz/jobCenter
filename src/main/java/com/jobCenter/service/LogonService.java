package com.jobCenter.service;


import com.jobCenter.domain.UserInfo;

/**
 * 描述：登录
 * 作者 ：kangzz
 * 日期 ：2016-11-26 16:00:23
 */
public interface LogonService {
    /**
     * 描述：验证登录
     * 作者 ：kangzz
     * 日期 ：2016-11-26 16:00:14
     */
    UserInfo checkLogon(String userCode, String userPwd);
}

