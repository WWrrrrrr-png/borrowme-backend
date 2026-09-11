package com.borrowme.auth.response;

public class MyInfoResponse {
		
	private Long id; 
	private String email; 
	private String name; 
	private String role;  
	
	public MyInfoResponse(Long id, String email, String name, String role) {
		this.id = id; 
		this.email = email; 
		this.name = name; 
		this.role = role;
	}  
	
	public Long getId() {
		return id;
	} 
	
	public String getEmail() {
		return email;
	} 
	
	public String getName() {
		return name;
	} 
	
	public String getRole() {
		return role;
	}
	
	
	
}