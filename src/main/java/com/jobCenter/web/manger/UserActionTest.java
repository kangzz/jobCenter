package com.jobCenter.web.manger;

import com.jobCenter.util.client.JobCenterCommonAction;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/UserAction")
public class UserActionTest extends JobCenterCommonAction {

	private static final Logger logger = Logger.getLogger(UserActionTest.class);

	@RequestMapping(value="getUser",method={RequestMethod.GET,RequestMethod.POST})
	public void notify(HttpServletRequest request, HttpServletResponse response) {
		logger.info("使用业务系统自定义的方法~~~~~~~~~");
		super.notify(request, response);
	}

	public static void main(String[] ary){
		int num = 15;
		int by = (int)(num*1.5);
		logger.info("===="+by);
	}
}
