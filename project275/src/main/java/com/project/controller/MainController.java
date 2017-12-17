package com.project.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	
	@GetMapping("/")
	public String home(HttpServletRequest request){
		request.setAttribute("mode", "MODE_HOME");
		return "index";
		/*System.out.println("rr");
		request.setAttribute("mode", "Home_Page");
		return "mainPage";*/
	}
	
	@GetMapping("/mainPage")
	public String homePage(HttpServletRequest request){
		System.out.println("rr");
		request.setAttribute("mode", "Home_Page");
		return "mainPage";
	}
	
}
