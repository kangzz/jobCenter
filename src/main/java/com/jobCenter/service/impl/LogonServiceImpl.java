package com.jobCenter.service.impl;

import com.jobCenter.domain.*;
import com.jobCenter.enums.IsType;
import com.jobCenter.mapper.*;
import com.jobCenter.service.LogonService;
import com.jobCenter.util.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(value = "logonService")
@Transactional
public class LogonServiceImpl implements LogonService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    private final static Logger logger = Logger.getLogger(LogonServiceImpl.class);

    /**
     * 描述：验证登录
     * 作者 ：kangzz
     * 日期 ：2016-11-26 16:00:14
     */
    public UserInfo checkLogon(String userCode, String userPwd){
        UserInfo userQuery = new UserInfo();
        userQuery.setUserCode(userCode);
        userQuery.setIsDel(IsType.NO.getValue());
        List<UserInfo> userInfoList = userInfoMapper.selectUserInfoByRecord(userQuery);
        if(userInfoList == null || userInfoList.isEmpty()){
            throw new RuntimeException("用户不存在!");
        }
        UserInfo userInfoDb = userInfoList.get(0);
        if(MD5Util.encodeMD5(userPwd,"").equals(userInfoDb.getUserPwd())){
            return userInfoDb;
        }else{
            throw new RuntimeException("密码错误!");
        }

    }
}