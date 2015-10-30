package com.ry.pfb.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Test {

	@RequestMapping("/test")
	@ResponseBody
	public ModelAndView method(HttpServletRequest re,HttpServletResponse res) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView("test");
		mv.addObject("name", "测试");
		return mv ;
	}
}
