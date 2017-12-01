package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.UserRepository;
import com.project.model.User;



@Service
@Transactional
public class LoginRegisterService {
	
	private final UserRepository userRepository;
	
	public LoginRegisterService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User login(String email, String password){
		User user = userRepository.findUser(email,password);
		if(user==null){
			return null;
		}
		return user;
	}

	public User register(String email, String password, String firstName, String lastName) {
		// TODO Auto-generated method stub
		
		User user = new User(email,password,firstName,lastName);
		userRepository.save(user);
		return user;
	}	
}
