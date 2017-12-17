package com.project.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.project.model.User;
import com.project.service.UserServiceimpl;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@RestController
public class MainRestController {
	

	@Autowired
	private UserServiceimpl userServiceimpl;
	
	@Autowired 
	private HttpSession httpSession;
	
	
	@RequestMapping("/userLogin")
	public ResponseEntity<Object> login(HttpServletResponse response,
			HttpServletRequest request,
			@RequestBody String s) throws ParseException{
		
		ModelMap map = new ModelMap();
 		JSONParser parser = new JSONParser();
 		JSONObject json = (JSONObject) parser.parse(s);
 		
		User user = userServiceimpl.login(json.getAsString("email"), json.getAsString("password"));
 		if(user==null){
 			map.addAttribute("statusCode","404");
 			return new ResponseEntity<Object>(map,HttpStatus.OK);
 		}
 		
 		
 		//Follow this to set the Http Sessions
 		HttpSession httpSession = request.getSession();
		httpSession.putValue("User_Email", user.getEmail());
		//End of storage to sessions 
		
 		map.addAttribute("statusCode","200");
 		map.addAttribute("username", user.getEmail());
 		map.addAttribute("UserFirstName", user.getFirstName());
 		return new ResponseEntity<Object>(map,HttpStatus.OK);
	}
	
	
	@RequestMapping("/userRegistration")
	 	public ResponseEntity<Object> register(HttpServletResponse response, HttpServletRequest request, @RequestBody String s) throws ParseException{
	 		ModelMap map = new ModelMap();
	 		JSONParser parser = new JSONParser();
	 		JSONObject json = (JSONObject) parser.parse(s);
	 		System.out.println("user is"+json);
	 		User user = userServiceimpl.register(json.getAsString("regEmail"),json.getAsString("password"),json.getAsString("firstName"),json.getAsString("lastName"));
	 		if(user ==null){
	 			map.addAttribute("statusCode","404");
	 			return new ResponseEntity<Object>(map,HttpStatus.OK);
	 		}
	 		map.addAttribute("statusCode","200");
	 		map.addAttribute("username", user.getEmail());
	 		map.addAttribute("UserFirstName", user.getFirstName());
	 		return new ResponseEntity<Object>(map,HttpStatus.OK);
	 	}
	/*@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}*/
	
	/*@RequestMapping("/mainPage")
	public String homePage(HttpServletRequest request){
		System.out.println("rr");
		request.setAttribute("mode", "Home_Page");
		return "mainPage";
	}*/
}
