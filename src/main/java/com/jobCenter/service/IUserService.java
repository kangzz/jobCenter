package com.jobCenter.service;


import com.jobCenter.domain.User;

/**
 * 
 * 类说明   : service
 * 作者        :kangzz
 * 日期        :2016-1-6下午12:36:06
 * 版本号    : V1.0
 */
public interface IUserService {

	/**
	 * 通过userId查询用户
	 */
	public User getUserById(String id);
}
