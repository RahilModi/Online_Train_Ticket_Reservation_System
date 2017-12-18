package com.project.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	
	@Autowired 
	private HttpSession httpSession;
	
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
		
		//Follow this to get the session data
 		HttpSession httpSession = request.getSession();
		String userEmail = httpSession.getAttribute("User_Email").toString();
		request.setAttribute("mode", "Home_Page");
		request.setAttribute("email", userEmail);
		return "mainPage";
	}
	
	@GetMapping("/adminMainPage")
	public String adminHomePage(HttpServletRequest request){
		
		//Follow this to get the session data
 		HttpSession httpSession = request.getSession();
		String userEmail = httpSession.getAttribute("User_Email").toString();
		request.setAttribute("mode", "Home_Page");
		request.setAttribute("email", userEmail);
		return "adminMainPage";
	}
	
}
