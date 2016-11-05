package com.jobCenter.web.manger;

import com.jobCenter.util.client.JobCenterCommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：业务系统执行定时任务
 * 作者 ：kangzz
 * 日期 ：2016-03-22 22:32:06
 */
@Controller
@RequestMapping(value="/serviceSystem")
public class DoJobsController extends JobCenterCommonController {
	/**
	 * 描述：执行定时任务
	 * 作者 ：kangzz
	 * 日期 ：2016-03-22 22:32:50
	 */
	@RequestMapping(value="notify.do",method={RequestMethod.GET,RequestMethod.POST})
	public void notify(HttpServletRequest request, HttpServletResponse response) {
		super.notify(request, response);
	}


	public static void main(String[] aa){

		String bb = "str";
		changeString(bb);
		System.out.println(bb);

		Integer integerVal = 0;
		changeInteger(integerVal);
		System.out.println(integerVal);

		int intVal = 0;
		changeInt(intVal);
		System.out.println(intVal);

	}
	public static void changeString(String bb){
		bb = bb+"___str";
	}
	public static void changeInteger(Integer integerVal){
		integerVal = integerVal+123;
	}
	public static void changeInt(int intVal){
		intVal = intVal+234;
	}
}
