package com.project.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ModelAndView login(HttpServletResponse response,@RequestParam(value="email",required=true) String email,
			@RequestParam(value="password",required=true) String password,
			@RequestParam(value="firstName",required=true) String fname,
			@RequestParam(value="lastName",required=false) String lname){
		
		ModelMap map = new ModelMap();
		
		User user = userService.login(email, password);
 		if(user==null){
 			map.addAttribute("result","404");
 			return new ModelAndView(new MappingJackson2JsonView(), map);
 		}
 		map.addAttribute("result","200");
 		map.addAttribute("UserEmail", user.getEmail());
 		map.addAttribute("UserFirstName", user.getFirstName());
 		return new ModelAndView(new MappingJackson2JsonView(), map);
	}
	
	
	@RequestMapping("/CusrRegistration")
	 	public ModelAndView register(HttpServletResponse response, 
	 			@RequestParam(value="regEmail",required=true) String email,
	 			@RequestParam(value="password") String password,
	 			@RequestParam(value="firstName") String firstName,
	 			@RequestParam(value="lastName") String lastName){
	 	
	 		ModelMap map = new ModelMap();
	 		User user = userService.register(email,password,firstName,lastName);
	 		if(user ==null){
	 			map.addAttribute("result","404");
	 			return new ModelAndView(new MappingJackson2JsonView(),map);
	 		}
	 		map.addAttribute("result","200");
	 		map.addAttribute("UserEmail", user.getEmail());
	 		map.addAttribute("UserFirstName", user.getLastName());
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
	
//	@GetMapping("/all-tasks")
//	public String allTasks(){
//		return taskService.findAll().toString();
//	}
//	
//	@GetMapping("/save-task")
//	public String saveTask(@RequestParam String name, @RequestParam String desc){
//		Task task = new Task(name, desc, new Date(), false);
//		taskService.save(task);
//		return "Task saved!";
//	}
//	
//	@GetMapping("/delete-task")
//	public String saveTask(@RequestParam int id){
//		taskService.delete(id);
//		return "Task deleted!";
//	}
}
