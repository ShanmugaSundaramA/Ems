package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeleteController {

	@RequestMapping("/delete")
	public String viewDetails() {
		return "Delete";
	}
	
}
