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
	public ModelAndView login(HttpServletRequest re, HttpServletResponse res){
		String name = re.getParameter("Name");
		String pwd = re.getParameter("Password");
		if("a".equals(name) && "a".equals(pwd)){
			ModelAndView mv = new ModelAndView("redirect:success");
			return mv;
		}else{
			ModelAndView mv = new ModelAndView("redirect:gologin");
			mv.addObject("flag", "fail");
			return mv;
		}
	}
	
	@RequestMapping("/gologin")
	public ModelAndView goLogin(HttpServletRequest re, HttpServletResponse res){
		ModelAndView mv = new ModelAndView("customer/login");
		return mv;
	}
	
	@RequestMapping("/success")
	public ModelAndView success(HttpServletRequest re, HttpServletResponse res){
		ModelAndView mv = new ModelAndView("customer/success");
		return mv;
	}
	
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest re, HttpServletResponse res){
		ModelAndView mv = new ModelAndView("customer/register");
		return mv;
	}
	
	@RequestMapping("/aboutus")
	public ModelAndView aboutus(HttpServletRequest re, HttpServletResponse res){
		ModelAndView mv = new ModelAndView("customer/aboutus");
		return mv;
	}
	
	@RequestMapping("/ask")
	public ModelAndView ask(HttpServletRequest re, HttpServletResponse res){
		ModelAndView mv = new ModelAndView("customer/ask");
		return mv;
	}
}
