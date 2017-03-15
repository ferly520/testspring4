package com.dota.framework.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dota.framework.domain.LoginMapper;
import com.dota.framework.security.CustomLoginInterface;
import com.sysbusiness.users.domain.UserMapper;
import com.sysbusiness.users.model.User;

public class LoginSerivce extends BaseService implements CustomLoginInterface {
	
	private LoginMapper loginMapper;

	public LoginMapper getLoginMapper() {
		return loginMapper;
	}

	public void setLoginMapper(LoginMapper loginMapper) {
		this.loginMapper = loginMapper;
	}

	@Override
	public HashMap<String, Object> queryUserDetailForUserCode(String userCode) {
		return loginMapper.queryUserForUserCode(userCode);
	}

	@Override
	public List<HashMap<String, Object>> queryRoleDetailForUserCode(String userCode) {
		// TODO Auto-generated method stub
		return loginMapper.queryRoleDetailForUserCode(userCode);
	}

	@Override
	public List<HashMap<String, Object>> queryRoleUrl() {
		// TODO Auto-generated method stub
		return loginMapper.queryRoleUrl();
	}

	@Override
	public void changeUserToSecurityBean(HashMap<String, Object> user) {
		// TODO Auto-generated method stub
		
	}

}
