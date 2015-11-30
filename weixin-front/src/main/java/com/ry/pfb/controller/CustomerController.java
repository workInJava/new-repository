package com.ry.pfb.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.ry.pfb.common.Result;
import com.ry.pfb.util.AxisUtils;

import me.chanjar.weixin.common.util.StringUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@RequestMapping("/login")
	public String login(HttpServletRequest re, HttpServletResponse res,Model model){
		String name = (String)re.getParameter("useName");
		String pwd = (String)re.getParameter("password");
		if(StringUtils.isBlank(name)&&StringUtils.isBlank(pwd)){
			model.addAttribute("msg", "用户名密码不能为空");
			return "customer/login";
		}
		String jsonStr = (String)AxisUtils.axisClient(Arrays.asList(name,pwd),"checkLogin");
		Result result  = new Gson().fromJson(jsonStr, Result.class);
		if(result!=null){
			model.addAttribute("useName", name);
			if(result.isResult()){
				LOGGER.info("登陆成功");
				HttpSession session = re.getSession(true);
				session.setAttribute("loginedUser", name);
				return "customer/success";
			}else {
				model.addAttribute("password",pwd);
				model.addAttribute("flag", result.isResult());
				model.addAttribute("msg", result.getResultMessage());
				return "customer/login";
			}
		}
		return "customer/login";
	}
	
	@RequestMapping("/gologin")
	public ModelAndView goLogin(HttpServletRequest re, HttpServletResponse res){
		ModelAndView mv = new ModelAndView("customer/login");
		return mv;
	}
	
	@RequestMapping("/loginout")
	public String loginOut(){
		
		return "customer/login";
	}
	
}
