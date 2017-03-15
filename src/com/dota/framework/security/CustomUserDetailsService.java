package com.dota.framework.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dota.framework.dao.UserDetailsBean;

/**
 * <p>UserDetailService 给用户提供自定义的授权处理
 * @see UserDetailService
 */
public class CustomUserDetailsService implements UserDetailsService{
	
	private static Log log = LogFactory
			.getLog(CustomUserDetailsService.class);
	
	private CustomLoginInterface customLogin;
	
    public CustomLoginInterface getCustomLogin() {
		return customLogin;
	}

	public void setCustomLogin(CustomLoginInterface customLogin) {
		this.customLogin = customLogin;
	}

	//登陆验证时，通过username获取用户的所有权限信息，  
    //并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用  
    @Override
    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException {
	// TODO Auto-generated method stub
    	Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();   
    	HashMap<String,Object> userDetail = customLogin.queryUserDetailForUserCode(username);
    	userDetail.put("roles", customLogin.queryRoleDetailForUserCode(username));
    	log.debug("login user name:"+userDetail.toString());
    	
	     UserDetailsBean user = new UserDetailsBean(userDetail,true);   
	     return user;    
    }

   

}
