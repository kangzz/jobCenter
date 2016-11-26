package com.jobCenter.web.authority.logon;

import com.jobCenter.comm.GlobalVariable;
import com.jobCenter.domain.UserInfo;
import com.jobCenter.enums.IsType;
import com.jobCenter.model.authority.logon.UserAccount;
import com.jobCenter.service.LogonService;
import com.jobCenter.util.StringUtil;
import com.jobCenter.util.UserUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		if(UserUtil.getCurrentUser() != null){
			return "redirect:/index.do";
		}
		String userCode = request.getParameter("userCode");
		String userPwd = request.getParameter("userPwd");
		if(StringUtil.isBlank(userCode) || StringUtil.isBlank(userPwd)){
			logger.info("用户名或密码为空!");
			return "login";
		}
		try{
			UserInfo userInfo = logonService.checkLogon(userCode,userPwd);
			UserAccount userAccount =
					new UserAccount(userInfo.getId(),userInfo.getUserName(),userInfo.getUserCode()
							,userInfo.getUserPhone(),userInfo.getUserMail());
			request.getSession().setAttribute(GlobalVariable.SESSION_CURRENT_USER_KEY, userAccount);
			return "redirect:/index.do";
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			return "login";
		}
	}

	/**
	 * 
	 * 登录成功跳转此页面
	 *
	 * @author zhangshaobin
	 * @created 2013-1-29 下午1:21:14
	 *
	 */
	@RequestMapping("index.do")
	public String index() {
		UserAccount userAccount = UserUtil.getCurrentUser();


		return "index";
	}

	/**
	 * 
	 * 跳转到头部
	 *
	 * @author zhangshaobin
	 * @created 2013-1-29 上午10:58:36
	 *
	 * @return
	 */
	@RequestMapping("header")
	public String header(HttpSession session) {
		return "/include/header";
	}

	/**
	 * 
	 * 跳转到左边菜单
	 *
	 * @author zhangshaobin
	 * @created 2013-1-29 上午10:58:36
	 *
	 * @return
	 */
	@RequestMapping("leftside")
	public String leftSide(HttpServletRequest request, HttpSession session) {
		String pId = request.getParameter("projectid");
		if (pId == null) {

		}
		request.setAttribute("pId", pId);
		return "/include/leftside";
	}

	/**
	 * 
	 * 跳转到
	 *
	 * @author zhangshaobin
	 * @created 2013-1-29 上午10:58:36
	 *
	 * @return
	 */
	@RequestMapping("container")
	public String container() {
		return "/include/container";
	}

	/**
	 * 
	 * 退出
	 *
	 * @author zhangshaobin
	 * @created 2013-2-2 下午5:47:57
	 *
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		// session失效
		session.invalidate();
		return "login";
	}

}
