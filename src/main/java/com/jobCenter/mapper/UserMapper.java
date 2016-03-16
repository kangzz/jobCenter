package com.jobCenter.mapper;


import java.util.List;

import com.jobCenter.domain.User;
import org.springframework.stereotype.Repository;




/**   
 * @Title UserMapper.java 
 * @Package cn.ziroom.mybatis.mapper 
 * @Description: UserDao实体 
 * @author dfx  
 * @date 2015-11-9 下午2:32:01 
 * @version V1.0   
 */

@Repository
public interface UserMapper {

	/**
	 * 通过userId查询用户
	 */
	public User getUserById(String id) throws Exception ;
}
