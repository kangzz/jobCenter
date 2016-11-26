package com.jobCenter.model.authority.logon;

import java.io.Serializable;
/**
 * 描述：登录人信息
 * 作者 ：kangzz
 * 日期 ：2016-11-26 15:17:41
 */
public class UserAccount implements Serializable {

	private static final long serialVersionUID = -2033094831520882028L;
	private Long userId;//用户id

	private String userName; //用户名称
	
	private String userCode;//员工号
	
	private String userMail;//员工邮箱
	
	private String userPhone;//员工电话

	private UserAccount(){

	}
	public UserAccount(Long userId,String userName, String userCode, String userMail, String userPhone) {
		this.userId = userId;
		this.userName = userName;
		this.userCode = userCode;
		this.userMail = userMail;
		this.userPhone = userPhone;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Override
	public String toString() {
		return "UserAccount{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", userCode='" + userCode + '\'' +
				", userMail='" + userMail + '\'' +
				", userPhone='" + userPhone + '\'' +
				'}';
	}
}