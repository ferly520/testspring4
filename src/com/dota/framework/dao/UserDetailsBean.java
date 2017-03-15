package com.dota.framework.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

//自定义用户信息
public class UserDetailsBean implements UserDetails {
	/**
	 * 
	 * 用戶名
	 */

	private String username;

	/**
	 * 
	 * 密码
	 */

	private String password;

	/**
	 * 
	 * 角色
	 */

	private Collection<HashMap<String,?>> roles;
	
	
	//是否被禁用
	private boolean enabled=true;

	private static final String ROLE_ADMIN = "ROLE_ADMIN";

	//userDetail 用户信息与权限   enabled 用户是否被禁用
	public UserDetailsBean(HashMap<String,?> userDetail,boolean enabled){
		this.username = userDetail.get("USERCODE")+"";
		this.password = userDetail.get("USERPASSWORD")+"";
		this.enabled = enabled;
		this.roles = (Collection<HashMap<String,?>>)userDetail.get("roles");
	}
	

	/**
	 * 
	 * 获取当前用户的权限
	 * 
	 * 其实用户应该拥有多个角色,这里简单起见只用了一个String类型来表示
	 * 
	 * 其实用户 角色权限 资源三者可以各自创建对象并关联能实现一个非常复杂的权限控制
	 */

	public Collection<GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> grantedAuthoritys = new ArrayList<GrantedAuthority>();
		for(HashMap<String,?> role:roles){
			grantedAuthoritys.add(new GrantedAuthorityImpl(""+role.get("ROLECODE")));
		}

		return grantedAuthoritys;

	}

	public String getPassword() {

		return password;

	}

	public String getUsername() {

		return username;

	}

	public boolean isAccountNonExpired() {

		return true;

	}
	//账户是否未锁定
	public boolean isAccountNonLocked() {

		return true;

	}
	//证书是否未失效
	public boolean isCredentialsNonExpired() {

		return true;

	}

	public boolean isEnabled() {

		return enabled;

	}

	public void setUsername(String username) {

		this.username = username;

	}

	public void setPassword(String password) {

		this.password = password;

	}

	public void setEnabled(boolean enabled) {

		this.enabled = enabled;

	}
}
