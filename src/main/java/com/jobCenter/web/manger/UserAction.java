package com.jobCenter.web.manger;

import com.jobCenter.domain.User;
import com.jobCenter.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/UserAction")
public class UserAction {

	@Autowired
	private IUserService userService;
	private static final Logger logger = Logger.getLogger(UserAction.class);
	//@RequestMapping(value="/getUser",method=RequestMethod.GET)
	//@ResponseBody
	//public String getUser(@RequestParam("id") String id){
	@RequestMapping(value="getUser",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String getUser(HttpServletRequest request){

		logger.info("uuid:::::::"+request.getParameter("uuid"));
		//业务机器接受到请求
		//新开一个线程执行本次任务
		//返回本机已通知成功标志

		//System.out.println("id="+id);
		User user = userService.getUserById("1");
		System.out.println(user);
		return null;
	}

}
