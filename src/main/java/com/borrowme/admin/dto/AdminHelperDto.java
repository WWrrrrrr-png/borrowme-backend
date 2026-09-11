package com.borrowme.admin.dto;

import java.time.LocalDateTime;

public class AdminHelperDto {
   
	private Long id; 
	private String email; 
	private String name; 
	private String status; 
	private LocalDateTime createdAt; 
	
	public AdminHelperDto() {}  
	
	public Long getId() {return id;} 
	public String getEmail() {return email;} 
	public String getName() {return name;} 
	public String getStatus() { return status;} 
	public LocalDateTime getCreatedAt() { return createdAt;} 
	
	public void setId(Long id) {this.id = id;} 
	public void setEmail(String email) {this.email = email;}
	public void setName(String name) {this.name = name;} 
	public void setStatus(String status) {this.status = status;}  
	public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
}
