package com.jobCenter.web.manger;

import com.jobCenter.domain.User;
import com.jobCenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/UserAction")
public class UserAction {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/getUser",method=RequestMethod.GET)
	@ResponseBody
	public String getUser(@RequestParam("id") String id){
		System.out.println("id="+id);
		User user = userService.getUserById(id);
		System.out.println(user);
		return null;
	}

}
