package com.jobCenter.comm.shiro;

import com.jobCenter.comm.GlobalVariable;
import com.jobCenter.domain.UserInfo;
import com.jobCenter.model.authority.logon.UserAccount;
import com.jobCenter.service.LogonService;
import com.jobCenter.util.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：
 * 作者 ：kangzz
 * 日期 ：2016-11-27 12:55:50
 */
public class MyShiroFilter extends AccessControlFilter {
    @Autowired
    private LogonService logonService;
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            Subject subject = getSubject(request, response);
            Long userId = (Long) subject.getPrincipal();
            Session session=subject.getSession();
            UserAccount userAccount = UserUtil.getCurrentUser();
            if(userId != null && userAccount == null) {
                UserInfo userInfo = logonService.getUserInfoById(userId);
                userAccount =
                        new UserAccount(userInfo.getId(), userInfo.getUserName(), userInfo.getUserCode()
                                , userInfo.getUserPhone(), userInfo.getUserMail());
                session.setAttribute(GlobalVariable.SESSION_CURRENT_USER_KEY, userAccount);
            }
            return subject.getPrincipal() != null;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            //判断session里是否有用户信息
            if (httpServletRequest.getHeader("x-requested-with") != null
                    && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                //如果是ajax请求响应头会有，x-requested-with
                httpServletResponse.setHeader("sessionStatus", "session_timeout");//在响应头设置session状态
                return false;
            }
        }
        saveRequestAndRedirectToLogin(request, response);
        return false;
    }
}
