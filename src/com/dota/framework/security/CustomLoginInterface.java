package com.dota.framework.security;

import java.util.HashMap;
import java.util.List;

public interface CustomLoginInterface {
	
	//登陆查询
	HashMap<String,Object> queryUserDetailForUserCode(String userCode);
    //权限查询
	List<HashMap<String, Object>> queryRoleDetailForUserCode(String userCode);
	//访问路径查询
	List<HashMap<String, Object>> queryRoleUrl();
	//查询结构转换
	void changeUserToSecurityBean(HashMap<String,Object> user);
}
