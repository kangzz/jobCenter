package com.jobCenter.service;


import com.jobCenter.domain.UserInfo;
import com.jobCenter.model.authority.logon.MenuDto;

import java.util.List;

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

    UserInfo getUserInfo(UserInfo userInfo);

    UserInfo getUserInfoById(Long userId);
    /**
     * 描述：获取用户菜单权限
     * 作者 ：kangzz
     * 日期 ：2016-11-26 17:51:26
     */
    List<MenuDto> findMenuTreeByUserId(Long userId);
}

