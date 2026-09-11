package com.borrowme.auth.request;

public class SignupRequest {
		
	private String email; 
	private String password; 
	private String name; 
	private String gender; 
	private String phone;
	
	public SignupRequest() {
		
	}   
	
	public String getEmail() {
		return email;
	} 
	
	public String getPassword() {
		return password;
	} 

	public String getName() {
		return name;
	} 
	
	public String getGender() {
		return gender;
	}
	
	public String getPhone() {
		return phone;
	}

	
	public void setEmail(String email) {
		this.email = email;
	} 
	
	
	public void setPassword(String password) {
		this.password = password;
	}  
	
	
	public void setName(String name) {
		this.name = name;
	}

	
	public void setGender(String gender) {
		this.gender = gender;
	}

	
	public void setPhone(String phone) {
		this.phone = phone;
	}
}