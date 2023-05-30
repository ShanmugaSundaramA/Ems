package com.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login() {
		return "Login";
	}
	
	@RequestMapping("/Test")
	public String test() {
		return "Test";
	}
	
	@RequestMapping("/loginDataCheck")
	public String loginDataCheck(HttpServletRequest request) {
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		
		System.out.println(userName+" "+password);
		
		return "Welcome";
	}
}
