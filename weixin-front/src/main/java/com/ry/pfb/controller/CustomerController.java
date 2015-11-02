package com.ry.pfb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@RequestMapping("/login")
	public void login(HttpServletRequest re, HttpServletResponse res){
		String name = (String)re.getAttribute("Name");
		String pwd = (String)re.getAttribute("Password");
		if("a".equals(name) && "a".equals(pwd)){
			re.setAttribute("flag", "success");
		}else{
			re.setAttribute("flag", "fail");
		}
		try {
			re.getRequestDispatcher("success.jsp").forward(re,res);
		} catch (Exception e) {
			LOGGER.warn("登录出错");
		}
	}
	
	@RequestMapping("/gologin")
	public ModelAndView goLogin(HttpServletRequest re, HttpServletResponse res){
		ModelAndView mv = new ModelAndView("customer/login");
		return mv;
	}
}
