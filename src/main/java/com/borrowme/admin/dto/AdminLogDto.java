package com.borrowme.admin.dto;

import java.time.LocalDateTime;

public class AdminLogDto {  
	
	private Long id; 
	private Long adminId; 
	private Long targetRequestId; 
	private String action; 
	private String reason; 
	private LocalDateTime createdAt;
	

	public AdminLogDto() {}
	
	public Long getId() {return id;}  
	public Long getAdminId() {return adminId;} 
	public Long getTargetRequestId() {return targetRequestId;} 
	public String getAction() {return action;} 
	public String getReason() {return reason;} 
	public LocalDateTime getCreatedAt() {return createdAt;} 
	
	
	public void setId(Long id) {this.id = id;} 
	public void setAdminId(Long adminId) {this.adminId = adminId;}
	public void setTargetRequestId(Long targetRequestId) {this.targetRequestId = targetRequestId;}
	public void setAction(String action) {this.action = action;}
	public void setReason(String reason) {this.reason = reason;}
	public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
	 
	
	
}
