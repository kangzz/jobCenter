package com.jobCenter.service.impl;
import com.jobCenter.domain.User;
import com.jobCenter.mapper.UserMapper;
import com.jobCenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "userService") 
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	//private UserMSMapper userMSMapper;
	
	/**
	 * 通过userId查询用户
	 */
	public User getUserById(String id) {
		User user = null;
		try {
			user = userMapper.getUserById(id);
			//user = userMSMapper.getUserById(id);
		} catch (Exception e) {
			System.out.println("通过userId查询用户失败!");
			e.printStackTrace();
		}
		return user;
	}

}
