package com.jobCenter.domain;

import java.util.Date;

/**
 * 
 * 类说明   : 测试实体
 * 作者        :kangzz
 * 日期        :2016-1-6下午12:34:37
 * 版本号    : V1.0
 */
public class User {

	private String id;		//唯一标示
	private String name;	//姓名
	private String sex;		//性别
	private Integer age;	//年龄
	private Date birthday;	//生日
	private String addr;	//住址
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
}
