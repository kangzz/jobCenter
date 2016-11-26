package com.jobCenter.util;


import com.jobCenter.comm.GlobalVariable;
import com.jobCenter.model.authority.logon.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 描述：获取当前登录人信息
 * 作者 ：kangzz
 * 日期 ：2016-11-26 15:22:41
 */
public class UserUtil {
	protected static final Logger logger = LoggerFactory.getLogger(UserUtil.class);
	/**
	 * 描述：获取当前登录人信息
	 * 作者 ：kangzz
	 * 日期 ：2016-11-26 15:22:59
	 */
	public static UserAccount getCurrentUser() {
		UserAccount user = null ;
		try {
			user = (UserAccount) ((ServletRequestAttributes)
					RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute(GlobalVariable.SESSION_CURRENT_USER_KEY) ;
		} catch (Exception e) {
			logger.error("Session中没有取出当前用户！" , e) ;
		}
		return user ;
	}
}
