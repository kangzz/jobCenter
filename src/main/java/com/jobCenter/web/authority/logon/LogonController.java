package com.jobCenter.web.authority.logon;

import com.jobCenter.comm.GlobalVariable;
import com.jobCenter.domain.UserInfo;
import com.jobCenter.model.authority.logon.MenuDto;
import com.jobCenter.model.authority.logon.UserAccount;
import com.jobCenter.service.LogonService;
import com.jobCenter.util.MD5Util;
import com.jobCenter.util.StringUtil;
import com.jobCenter.util.UserUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 描述：登录控制
 * 作者 ：kangzz
 * 日期 ：2016-11-26 14:51:49
 */
@Controller
@RequestMapping("/")
public class LogonController {
	private final static Logger logger = Logger.getLogger(LogonController.class);
	@Autowired
	private LogonService logonService;
	/**
	 * 描述：登录
	 * 作者 ：kangzz
	 * 日期 ：2016-11-26 14:49:25
	 */
	@RequestMapping(value = "login.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(HttpServletRequest request) {
		String userCode = request.getParameter("userCode");
		String userPwd = request.getParameter("userPwd");
		if(StringUtil.isBlank(userCode) || StringUtil.isBlank(userPwd)){
			logger.info("用户名或密码为空!");
			return "login";
		}
		try{
			UserInfo userInfoQuery = new UserInfo();
			userInfoQuery.setUserCode(userCode);
			UserInfo userInfo = logonService.getUserInfo(userInfoQuery);
			UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getId()+"", MD5Util.encodeMD5(userPwd,""));
			Subject currentUser = SecurityUtils.getSubject();
			if (!currentUser.isAuthenticated()){
				//使用shiro来验证
				token.setRememberMe(true);
				currentUser.login(token);//验证角色和权限
			}
		}catch(Exception ex){
			logger.error("",ex);
			return "login";
		}
		return "redirect:/index.do";
	}
	/**
	 * 描述：登录成功跳转首页
	 * 作者 ：kangzz
	 * 日期 ：2016-11-26 17:52:42
	 */
	@RequestMapping("index.do")
	public String index(HttpServletRequest request) {
		Subject subject=SecurityUtils.getSubject();
		Session session=subject.getSession();
		UserAccount userAccount = UserUtil.getCurrentUser();
		if(userAccount == null) {
			Long userId = (Long) subject.getPrincipal();
			UserInfo userInfo = logonService.getUserInfoById(userId);
			userAccount =
					new UserAccount(userInfo.getId(), userInfo.getUserName(), userInfo.getUserCode()
							, userInfo.getUserPhone(), userInfo.getUserMail());
			session.setAttribute(GlobalVariable.SESSION_CURRENT_USER_KEY, userAccount);
			userAccount = UserUtil.getCurrentUser();
			logger.info(userAccount.toString());
		}
		boolean has = subject.hasRole("123");
		List<MenuDto> menuList = logonService.findMenuTreeByUserId(userAccount.getUserId());
		request.setAttribute("menuList", menuList);
		return "index";
	}
	/**
	 * 描述：退出
	 * 作者 ：kangzz
	 * 日期 ：2016-11-26 17:52:58
	 */
	@RequestMapping(value = "logout.do")
	public String logout(HttpSession session) {
		// session失效
		session.invalidate();
		return "login";
	}

}
