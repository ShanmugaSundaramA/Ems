package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EditController {

	@RequestMapping("/edit")
	public String viewDetails() {
		return "Edit";
	}
	
}
