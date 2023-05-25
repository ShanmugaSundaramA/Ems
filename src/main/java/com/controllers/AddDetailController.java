package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddDetailController {

	@RequestMapping("/add")
	public String addDetails() {
		return "Add";
	}
}
