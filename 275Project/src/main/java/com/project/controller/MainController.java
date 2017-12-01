/*package com.project.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.project.model.User;
import com.project.service.LoginRegisterService;

@EnableAutoConfiguration
@RestController
public class MainController {

	@Autowired
	private LoginRegisterService loginRegisterService;


	@RequestMapping("/mainpage/{name}")
	public ModelAndView mainpage(HttpServletResponse response,@PathVariable(value="name") String userName){
		ModelMap map = new ModelMap();
		System.out.println("\n\n User Name: "+userName+"\n\n");
		return new ModelAndView(new MappingJackson2JsonView(),map);
	}
	
	@RequestMapping("/CusrLogin")
	public ModelAndView login(HttpServletResponse response,@RequestParam(value="emailId",required=true) String email,@RequestParam(value="loginPassword") String password){
		ModelMap map = new ModelMap();
		User user = loginRegisterService.login(email, password);
		if(user==null){
			map.addAttribute("result","404");
			return new ModelAndView(new MappingJackson2JsonView(), map);
		}
		map.addAttribute("result","200");
		map.addAttribute("UserEmail", user.getUser_email());
		map.addAttribute("UserFirstName", user.getUser_firstname());
		return new ModelAndView(new MappingJackson2JsonView(), map);
	}
	
	
	"regEmail":$scope.regEmail,
	"password":password,
	"firstName":$scope.firstName,
	"lastName":$scope.lastName,
	@RequestMapping("/CusrRegistration")
	public ModelAndView register(HttpServletResponse response, 
			@RequestParam(value="regEmail",required=true) String email,
			@RequestParam(value="password") String password,
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName") String lastName){
		
		ModelMap map = new ModelMap();
		User user = loginRegisterService.register(email,password,firstName,lastName);
		if(user ==null){
			map.addAttribute("result","404");
			return new ModelAndView(new MappingJackson2JsonView(),map);
		}
		map.addAttribute("result","200");
		map.addAttribute("UserEmail", user.getUser_email());
		map.addAttribute("UserFirstName", user.getUser_firstname());
		return new ModelAndView(new MappingJackson2JsonView(),map);	
	}


}
*/