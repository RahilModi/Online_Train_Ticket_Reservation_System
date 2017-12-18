package com.project.service;

import java.util.Arrays;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.project.dao.RoleRepository;
import com.project.dao.UserRepository;
import com.project.model.Role;
import com.project.model.User;

@Service
@Transactional
public class UserServiceimpl implements UserServices{
	
	private final UserRepository userRepo;
	
	@Autowired 
	private RoleRepository roleRepository;
	
	
	public UserServiceimpl(UserRepository userRepo){
		this.userRepo = userRepo;
	}

	public User register(String email, String password, String firstName, String lastName) {
		
		for(User p:userRepo.findAll()){
			if(p.getEmail().equals(email)){
				return null;
			}
		}	
		User user = new User(email,password,firstName,lastName);
		userRepo.save(user);
		Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepo.save(user);
		return user;
	}

	public User login(String email, String password) {
		
		for(User p:userRepo.findAll()){
			if(p.getEmail().equals(email) && p.getPassword().equals(password)){
				return p;
			}
		}
		return null;
	}

	
	//Facebook Login Service
	public User addOauthFacebookLogin(String user_id, String name) {
		
		for(User p:userRepo.findAll()){
			if(p.getEmail().equals(user_id)){
				return p;
			}
		}
		User usr = new User(user_id,name);
		userRepo.save(usr);
		Role userRole = roleRepository.findByRole("USER");
		usr.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepo.save(usr);
		return usr;
	}

	//Google Login Service
	public User addOauthGoogleLogin(String email, String firstName, String lastName) {
		for(User p:userRepo.findAll()){
			if(p.getEmail().equals(email)){
				return p;
			}
		}
		User usr = new User(email,firstName,lastName);
		userRepo.save(usr);
		Role userRole = roleRepository.findByRole("USER");
		usr.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepo.save(usr);
		return usr;
	}


}
