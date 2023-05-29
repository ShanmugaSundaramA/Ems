package com.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

	@RequestMapping("/register")
	public String register() {
		return "Register";
	}

	@RequestMapping("/registerDataCheck")
	public String registerDataCheck(HttpServletRequest request) {

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String re_password = request.getParameter("re-password");
		
		System.out.println(userName + " " + password + " " + re_password);
		
		return "Login";
	}

}
