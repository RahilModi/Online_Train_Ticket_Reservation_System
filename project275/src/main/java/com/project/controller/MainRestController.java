package com.project.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.project.model.Role;
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
	
	@RequestMapping("/userLogout")
	public ResponseEntity<Object>  logout(HttpServletResponse response, HttpServletRequest request) throws IOException{
		System.out.println("reached here");
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		/*response.sendRedirect("index");*/
		return null;
	}
	
	
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
		httpSession.putValue("User", user);
		//End of storage to sessions 
		
		 Iterator iterator = user.getRoles().iterator(); 
		   // check values
		   while (iterator.hasNext()){
			   Role role = (Role) iterator.next();
			   if(role.getRole().contains("USER")){
					map.addAttribute("role","USER");
					System.out.println("reached 11");
				}else if(role.getRole().contains("ADMIN")){
					map.addAttribute("role","ADMIN");
				}
		   System.out.println("Value: "+role.getRole() + " ");  
		   }
		
		/*if(user.getRoles().contains("USER")){
			map.addAttribute("role","USER");
			System.out.println("reached 11");
		}else if(user.getRoles().contains("ADMIN")){
			map.addAttribute("role","ADMIN");
		}*/
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
     		HttpSession httpSession = request.getSession();
    		httpSession.putValue("User_Email", user.getEmail());
			httpSession.putValue("User", user);
	 		map.addAttribute("statusCode","200");
	 		map.addAttribute("username", user.getEmail());
	 		map.addAttribute("UserFirstName", user.getFirstName());
	 		return new ResponseEntity<Object>(map,HttpStatus.OK);
	 	}
	@RequestMapping("/user")
	public Principal user(Principal principal, HttpServletRequest request) throws ParseException {
		
			OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
	        Authentication authentication = oAuth2Authentication.getUserAuthentication();
	        Map<String, String> details = new LinkedHashMap<>();
	        details = (Map<String, String>) authentication.getDetails();
	        if(details.get("id")!=null){
	        	System.out.println("reached 1");
	        	System.out.println("The details are: "+details.get("id"));
	        	User user = userServiceimpl.addOauthFacebookLogin(details.get("id"),details.get("name"));
	     		HttpSession httpSession = request.getSession();
	    		httpSession.putValue("User_Email", user.getEmail());
	        }else if(details.get("email")!=null){
	        	System.out.println("reached 2");
	        	System.out.println("The details are: "+details.get("email"));
	        	User user = userServiceimpl.addOauthGoogleLogin(details.get("email"),details.get("given_name"),details.get("family_name"));
	     		HttpSession httpSession = request.getSession();
	    		httpSession.putValue("User_Email", user.getEmail());
	        }
	        System.out.println("The details are: "+details);
	        Map<String, String> map = new LinkedHashMap<>();
		return principal;
	}
	
	/*@RequestMapping("/mainPage")
	public String homePage(HttpServletRequest request){
		System.out.println("rr");
		request.setAttribute("mode", "Home_Page");
		return "mainPage";
	}*/
}
