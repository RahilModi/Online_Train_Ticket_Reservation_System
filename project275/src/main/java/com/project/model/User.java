package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name="user_details")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    @Column(name="email",unique=true)
	private String email;
    private String password;
	private String firstName;
	private String lastName;
	public User(){
		
	}
	
	//Constructor for the Facebook Login
	public User(String email, String firstName){
		this.email = email;
		this.firstName = firstName;
	}
	
	
	//Constructor for Google Login
	public User(String email, String firstName, String lastName){
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	//Constructor for normal login
	public User(String email, String password, String firstName, String lastName){
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}



