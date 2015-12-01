package com.ry.pfb.fiter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 
 */
public class SessionFilter extends OncePerRequestFilter {

	private final static Logger LOGGER = LoggerFactory.getLogger(SessionFilter.class);
	public static final String PARAM_NAME_EXCLUSIONS = "exclusions";
	private Set<String> excludesPattern;
	private String contextPath;
	private String sessionKey;

	@Override
	protected void initFilterBean() throws ServletException {
		LOGGER.info("初始化过滤参数");
		ServletContext context = this.getFilterConfig().getServletContext();
		sessionKey = this.getFilterConfig().getInitParameter("sessionKey");
		if (context.getMajorVersion() == 2 && context.getMinorVersion() < 5) {
			contextPath = null;
		}
		contextPath = context.getContextPath();
		if (contextPath == null || contextPath.length() == 0) {
			contextPath = "/";
		}
		String exclusions = this.getFilterConfig().getInitParameter(PARAM_NAME_EXCLUSIONS);
		if (exclusions != null && exclusions.trim().length() != 0) {
			excludesPattern = new HashSet<String>(Arrays.asList(exclusions.split("\\s*,\\s*")));
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		if (isExclusion(requestURI)) {
			filterChain.doFilter(request, response);
			return;
		}
		HttpSession session = request.getSession(false);
		if (session == null || null == session.getAttribute(sessionKey)) {
			response.sendRedirect(contextPath + "/customer/gologin");
		}else if(matches(contextPath+"/",requestURI)){
			response.sendRedirect(contextPath + "");
		}else {
			filterChain.doFilter(request, response);
		}
	}

	public boolean isExclusion(String requestURI) {
		if (excludesPattern == null) {
			return false;
		}

		if (contextPath != null && requestURI.startsWith(contextPath)) {
			requestURI = requestURI.substring(contextPath.length());
			if (!requestURI.startsWith("/")) {
				requestURI = "/" + requestURI;
			}
		}

		for (String pattern : excludesPattern) {
			if (this.matches(pattern, requestURI)) {
				return true;
			}
		}
		return false;
	}

	private static boolean matches(String pattern, String source) {
		if (pattern == null || source == null) {
			return false;
		}
		pattern = pattern.trim();
		source = source.trim();
		if (pattern.endsWith("*")) {
			int length = pattern.length() - 1;
			if (source.length() >= length) {
				if (pattern.substring(0, length).equals(source.substring(0, length))) {
					return true;
				}
			}
		} else if (pattern.startsWith("*")) {
			int length = pattern.length() - 1;
			if (source.length() >= length && source.endsWith(pattern.substring(1))) {
				return true;
			}
		} else if (pattern.contains("*")) {
			int start = pattern.indexOf("*");
			int end = pattern.lastIndexOf("*");
			if (source.startsWith(pattern.substring(0, start)) && source.endsWith(pattern.substring(end + 1))) {
				return true;
			}
		} else {
			if (pattern.equals(source)) {
				return true;
			}
		}
		return false;
	}
}