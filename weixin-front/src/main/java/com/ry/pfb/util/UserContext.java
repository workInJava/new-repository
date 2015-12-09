package com.ry.pfb.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ry.pfb.common.UserVo;

public class UserContext {

	private static String SESSIONKEY = "sessionKey";
	
	public static UserVo getUserVo(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		UserVo userVo = (UserVo)request.getSession().getAttribute(PropertiesUtils.getValue(SESSIONKEY));
		if(userVo==null){
			//request.getCookies();
			
		}
		return userVo;
	}
}
