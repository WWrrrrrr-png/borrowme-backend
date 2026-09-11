package com.borrowme.user.dto;

import java.time.LocalDateTime;

public class UserRequestDto {
   
	private Long id; 
	private Long userId; 
	private String title;  
	private String content;
	private Integer amount; 
	private String status; 
	private String adminComment; 
	private LocalDateTime createdAt;  
	
	public UserRequestDto() {
		
	} 
	public Long getId() {
		return id;
	}  
	
	public Long getUserId() {
		return userId;
	}  
	
	public String getTitle() {
		return title;
	} 
	
	public String getContent() {
		return content;
	}   
	
	public String getStatus() {
		return status;
	}  
	
	public String getAdminComment() {
		return adminComment;
	} 
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	} 
	
	public Integer getAmount() {
	    return amount;
	}

	
	
	public void setId(Long id) {
		this.id = id;
	}  
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}  
	
	
	public void setTitle(String title) {
		this.title = title;
	} 
	
	public void setContent(String content) {
		
		this.content = content;
	} 
	 
	public void setStatus(String status) {
		this.status = status;
	}  
	
	public void setAdminComment(String adminComment) {
		this.adminComment = adminComment;
	} 
	  	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setAmount(Integer amount) {
	    this.amount = amount;
	}
}
