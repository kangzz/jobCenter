package com.jobCenter.service.impl;

import com.jobCenter.domain.*;
import com.jobCenter.enums.IsType;
import com.jobCenter.mapper.*;
import com.jobCenter.model.authority.logon.MenuDto;
import com.jobCenter.service.LogonService;
import com.jobCenter.util.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public UserInfo getUserInfo(UserInfo userInfo){
        userInfo.setIsDel(IsType.NO.getValue());
        List<UserInfo> userInfoList = userInfoMapper.selectUserInfoByRecord(userInfo);
        if(userInfoList == null || userInfoList.isEmpty()){
            throw new RuntimeException("用户不存在!");
        }
        return userInfoList.get(0);
    }
    public UserInfo getUserInfoById(Long userId){
        return userInfoMapper.selectByPrimaryKey(userId);
    }
    /**
     * 描述：获取用户菜单权限
     * 作者 ：kangzz
     * 日期 ：2016-11-26 17:51:26
     */
    public List<MenuDto> findMenuTreeByUserId(Long userId){
        List<MenuDto> list = new ArrayList<MenuDto>();
        List<MenuDto> firstChildList = new ArrayList<MenuDto>();
        MenuDto firstChild1 = new MenuDto(2,"定时任务列表","/job/jobList.do",null);
        MenuDto firstChild2 = new MenuDto(3,"心跳信息列表","/job/heartBeatList.do",null);
        firstChildList.add(firstChild1);
        firstChildList.add(firstChild2);
        MenuDto first = new MenuDto(1,"定时任务管理","",firstChildList);
        list.add(first);
        return list;
    }
}
