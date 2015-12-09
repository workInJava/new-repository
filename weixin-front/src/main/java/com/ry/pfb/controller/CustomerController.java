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
import com.ry.pfb.common.UserVo;
import com.ry.pfb.util.AxisUtils;
import com.ry.pfb.util.PropertiesUtils;

import me.chanjar.weixin.common.util.StringUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	private static String SESSIONKEY = "sessionKey";

	@RequestMapping("/login")
	public String login(HttpServletRequest re, HttpServletResponse res,Model model){
		String userName = (String)re.getParameter("useName");
		String pwd = (String)re.getParameter("password");
		if(StringUtils.isBlank(userName)&&StringUtils.isBlank(pwd)){
			model.addAttribute("msg", "用户名密码不能为空");
			return "customer/login";
		}
		Object jsonStr = AxisUtils.axisClient(Arrays.asList(userName,pwd),"checkLogin");
		if(jsonStr == null){
			model.addAttribute("useName",userName);
			model.addAttribute("password",pwd);
			model.addAttribute("msg", "稍等重试");
			return "customer/login";
		}
		Result result  = new Gson().fromJson((String)jsonStr, Result.class);
		if(null == result){
			model.addAttribute("useName",userName);
			model.addAttribute("password",pwd);
			model.addAttribute("msg", "稍等重试");
			return "customer/login";
		}
		if(!result.isResult()){
			model.addAttribute("useName",userName);
			model.addAttribute("password",pwd);
			model.addAttribute("msg", result.getResultMessage());
			return "customer/login";
		}
		HttpSession session = re.getSession();
		UserVo userVo = new UserVo();
		userVo.setUserName(userName);
		//TODO 加密
		userVo.setPassword(pwd);
		session.setAttribute(PropertiesUtils.getValue(SESSIONKEY), userVo);
		String requestURI = (String)session.getAttribute("url");
		if(StringUtils.isNotBlank(requestURI)){
			return "redirect:"+requestURI;
		}
		return "/test";
	}
	
	@RequestMapping("/gologin")
	public ModelAndView goLogin(HttpServletRequest re, HttpServletResponse res){
		
		HttpSession session = re.getSession();
		if(session != null){
			UserVo userVo = (UserVo)session.getAttribute(SESSIONKEY);
			
			
		}
		
		
		ModelAndView mv = new ModelAndView("customer/login");
		return mv;
	}
	
	@RequestMapping("/loginout")
	public String loginOut(HttpServletRequest re, HttpServletResponse res){
		HttpSession session = re.getSession(false);
		if(session!=null){
			session.invalidate();
		}
		return "customer/login";
	}
	
}
