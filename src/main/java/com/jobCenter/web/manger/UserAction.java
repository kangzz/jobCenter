package com.jobCenter.web.manger;

import com.alibaba.fastjson.JSONObject;
import com.jobCenter.comm.SystemConstant;
import com.jobCenter.domain.User;
import com.jobCenter.service.IUserService;
import com.jobCenter.util.CryptAES;
import com.jobCenter.util.MD5Util;
import com.jobCenter.util.SpringTool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value="/UserAction")
public class UserAction{

	@Autowired
	private IUserService userService;
	private static final Logger logger = Logger.getLogger(UserAction.class);



	//@RequestMapping(value="/getUser",method=RequestMethod.GET)
	//@ResponseBody
	//public String getUser(@RequestParam("id") String id){
	@RequestMapping(value="getUser2",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String getUser(HttpServletRequest request){

		logger.info("uuid:::::::"+request.getParameter("uuid"));
		//业务机器接受到请求
		//新开一个线程执行本次任务
		//返回本机已通知成功标志

		//System.out.println("id="+id);
		//User user = userService.getUserById("1");
		//System.out.println(user);
		return null;
	}


	private static Map<String, Thread> cache = new HashMap<String,Thread>();

	@RequestMapping(value="getUser",method={RequestMethod.GET,RequestMethod.POST})
	public void notify(HttpServletRequest request, HttpServletResponse response) {
		String serviceName = request.getParameter("serviceName");
		String uuid = request.getParameter("uuid");
		String jobId = request.getParameter("jobId");
		String securityCode = request.getParameter("securityCode");
		String linkId = request.getParameter("linkId");
		Thread.currentThread().setName(uuid);
		if(!checkIsValidVisit(uuid,linkId,securityCode)){
			logger.info("安全码校验失败!");
			printResult(response,"fail",2,"安全码校验失败",uuid);
			return;
		}else{
			logger.info("安全码校验成功!");
		}
		AbstractService testService = (AbstractService) SpringTool.getBean(serviceName);
		testService.setJobId(jobId);
		testService.setUuid(uuid);
		Thread t = new Thread(testService);
		t.setName(uuid);
		if(cache.containsKey(serviceName) && cache.get(serviceName).isAlive()) {
			printResult(response,"fail",1,"exist",uuid);
			return;
		}
		cache.put(serviceName,t);
		t.start();
		printResult(response,"success",0,"成功",uuid);
	}

	private synchronized void  printResult(HttpServletResponse response,String status,int code,String message,Object data) {
		try {
			JSONObject errorJson = new JSONObject();
			errorJson.put("status", status);
			errorJson.put("code", code);
			errorJson.put("message", message);
			errorJson.put("data", data);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(JSONObject.toJSONString(errorJson));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private synchronized Boolean checkIsValidVisit(String uuid, String jobLinkId, String securityCode){
		int jobLinkIdSubCount = jobLinkId.length() / SystemConstant.MD5_RATIO;
		jobLinkIdSubCount = jobLinkIdSubCount == 0 ? jobLinkId.length() : jobLinkIdSubCount;
		int uuidSubCount = uuid.length() / SystemConstant.MD5_RATIO;
		uuidSubCount = uuidSubCount == 0 ? uuid.length() : uuidSubCount;
		String securityStr = jobLinkId.substring(0, jobLinkIdSubCount) + uuid.substring(0, uuidSubCount);
		return securityCode.equals(MD5Util.encodeMD5(securityStr,SystemConstant.MD5_KEY));
	}

}
