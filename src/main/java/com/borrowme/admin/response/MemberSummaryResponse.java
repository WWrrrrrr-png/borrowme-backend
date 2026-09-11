package com.borrowme.admin.response;

public class MemberSummaryResponse {
		
	  private Long id; 
	  private String email;
	  private String name; 
	  private String status;
	  
	  public MemberSummaryResponse(Long id, String email, String name, String status) {
		  this.id = id; 
		  this.email = email; 
		  this.name = name; 
		  this.status = status;
	  } 
	  
	  public Long getId() { return id; } 
	  public String getEmail() { return email;} 
	  public String getName() {return name;} 
	  public String getStatus() {return status;}
}
