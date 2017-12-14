package com.project.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.project.model.User;
import com.project.service.UserService;

@RestController
public class MainRestController {
	

	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/userLogin")
	public ModelAndView login(HttpServletResponse response,
			@RequestParam(value="email",required=true) String email,
			@RequestParam(value="password",required=true) String password){
		
		ModelMap map = new ModelMap();
		
		User user = userService.login(email, password);
 		if(user==null){
 			map.addAttribute("statusCode","400");
 			return new ModelAndView(new MappingJackson2JsonView(), map);
 		}
 		map.addAttribute("statusCode","200");
 		map.addAttribute("username", user.getEmail());
 		map.addAttribute("UserFirstName", user.getFirstName());
 		return new ModelAndView(new MappingJackson2JsonView(), map);
	}
	
	
	@RequestMapping("/userRegistration")
	 	public ModelAndView register(HttpServletResponse response, 
	 			@RequestParam(value="regEmail",required=true) String email,
	 			@RequestParam(value="password") String password,
	 			@RequestParam(value="firstName") String firstName,
	 			@RequestParam(value="lastName") String lastName){
	 	System.out.println("reaches 1");
	 		ModelMap map = new ModelMap();
	 		System.out.println("user is"+email);
	 		//System.out.println(user.getEmail()+" "+user.getFirstName());
	 		/*User user = userService.register(email,password,firstName,lastName);
	 		if(user ==null){
	 			map.addAttribute("statusCode","404");
	 			return new ModelAndView(new MappingJackson2JsonView(),map);
	 		}
	 		map.addAttribute("statusCode","200");
	 		map.addAttribute("username", user.getEmail());
	 		map.addAttribute("UserFirstName", user.getFirstName());*/
	 		return new ModelAndView(new MappingJackson2JsonView(),map);	
	 	}
	/*@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}*/

	@GetMapping("/hello")
	public String hello(){
		return "Hello World!!!";
	}
}
