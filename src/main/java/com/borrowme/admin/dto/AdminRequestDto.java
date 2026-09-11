package com.borrowme.admin.dto;

import java.time.LocalDateTime;

public class AdminRequestDto {
	 
	private Long id; 
	private Long userId; 
	private String title; 
	private String content; 
	private String status; 
	private Integer amount;  
	private String adminComment; 
	private LocalDateTime createdAt; 
	
	public AdminRequestDto() {} 
	
	public Long getId() {return id;} 
	public Long getUserId() {return userId;} 
	public String getTitle() {return title; } 
	public String getContent() {return content;}  
	public String getStatus() {return status;}
	public Integer getAmount() {return amount;}    
	public String getAdminComment() {return adminComment;} 
	public LocalDateTime getCreatedAt() {return createdAt;}  
	
	
	public void setId(Long id) {this.id = id;} 
	public void setUserId(Long userId) {this.userId = userId;} 
	public void setTitle(String title) {this.title = title;}
	public void setContent(String content) {this.content = content;}
	public void setAmount(Integer amount) {this.amount = amount;} 
	public void setStatus(String status) {this.status = status;}
	public void setAdminComment(String adminComment) {this.adminComment = adminComment;} 
	public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
}
