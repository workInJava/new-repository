package com.ry.pfb.fiter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 登录过滤
 * 
 */
public class SessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Object obj = request.getSession().getAttribute("loginedUser");
		String url =  request.getRequestURI();
		if(null == obj || url.endsWith("/")){
			//response.sendRedirect("");
			//request.getRequestDispatcher("customer/gologin").forward(request, response);
			filterChain.doFilter(request, response);
		}else {
			filterChain.doFilter(request, response);
		}
	}

}