package com.dota.framework.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.dota.framework.dao.UserDetailsBean;
import com.dota.framework.domain.LoginMapper;

public class CustomFilterInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private static Log log = LogFactory
			.getLog(CustomFilterInvocationSecurityMetadataSource.class);
	
	private CustomLoginInterface customLogin;
	
    public CustomLoginInterface getCustomLogin() {
		return customLogin;
	}

	public void setCustomLogin(CustomLoginInterface customLogin) {
		this.customLogin = customLogin;
	}
	/**
	 * 
	 * 原本资源(url)与角色的对应关系需要从数据源中获取，这里做了简化
	 */

	private Map<String, Collection<ConfigAttribute>> resourceMap;

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		log.debug("requestUrl is " + url);
		return resourceMap.get(url);
	}
	
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		loadResourceMatchAuthority();
		return null;

	}

	public boolean supports(Class<?> clazz) {

		return true;

	}

	//初始化角色与url之间关系 在做权限判断时使用url去匹配登陆用户的角色
	private void loadResourceMatchAuthority() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();   
//        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();   
      
//        Collection<ConfigAttribute> attsno =new ArrayList<ConfigAttribute>();  
//        ConfigAttribute cano = new SecurityConfig("CGSUBJ_1");  
//        attsno.add(cano);  
//        resourceMap.put("/views/other.jsp", attsno);
       String roleCode=null;
       String url=null;
       String perUrl=null;
       List<HashMap<String,Object>> roleUrls = customLogin.queryRoleUrl();
       Collection<ConfigAttribute> atts1 = new ArrayList<ConfigAttribute>();
       ConfigAttribute ca1 = new SecurityConfig("ADMIN");  
       atts1.add(ca1);   
       resourceMap.put("/views/index.jsp", atts1);    
       Collection<ConfigAttribute> atts = null;   
       for(HashMap<String,Object> roleUrl:roleUrls){
    	   roleCode = roleUrl.get("ROLECODE")+"";
    	   url = roleUrl.get("FUNCTIONURL")+"";
    	   if(perUrl==null||!perUrl.equals(url)){
    		   atts = new ArrayList<ConfigAttribute>();
    		   resourceMap.put(url, atts); 
    	   }
    	   ConfigAttribute ca = new SecurityConfig(roleCode);  
    	   atts.add(ca);   
    	   perUrl = new String(url);
       }
       log.debug("role-url init:"+resourceMap);
	}

}
