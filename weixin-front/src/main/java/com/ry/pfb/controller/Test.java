package com.ry.pfb.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {

	@RequestMapping("/test")
	@ResponseBody
	public String method(HttpServletRequest re,HttpServletResponse res) throws UnsupportedEncodingException{
			String name="哈哈哈";
		return name;
	}
}
