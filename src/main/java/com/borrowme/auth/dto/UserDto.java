package com.borrowme.auth.dto;

import java.time.LocalDateTime;

public class UserDto {

	private Long id; 
	private String email; 
	private String password; 
	private String name; 
	private String gender;
	private String phone;
	private String status;
	private LocalDateTime createdAt; 
	
	public UserDto() {} 
	
	public Long getId(){
		return id; } 
	
	public String getEmail() {
		return email;} 
	
	public String getPassword() {
		return password;} 
	
	public String getName() { 
		return name; } 

	public String getGender() {
		return gender;
	}

	public String getPhone() {
		return phone;
	}
	
	public String getStatus() {
		return status;} 
	
	public LocalDateTime getCreatedAt() { 
		return createdAt;} 
	
	
	
	public void setId(Long id) {
		this.id = id;} 
	
	public void setEmail(String email) { 
		this.email = email;}

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
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}