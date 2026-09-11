package com.borrowme.admin.dto;

import java.time.LocalDateTime;

public class AdminMatchingViewDto {
   
	private Long matchingId; 
	private String requestTitle; 
	private String helperName;
	private String machingStatus; 
	private LocalDateTime createdAt;  
	
	public AdminMatchingViewDto() {} 
	
	public Long getMatchingId() {return matchingId;} 
	public void setMatchingId(Long matchingId) {this.matchingId = matchingId;} 
	
	public String getRequestTitle() {return requestTitle;} 
	public void setRequestTitle(String requestTitle) {this.requestTitle = requestTitle;} 
	
	public String getHelperName() {return helperName;} 
	public void setHelperName(String helperName) {this.helperName = helperName;} 
	
	public String getMachingStatus() {return machingStatus;} 
	public void setMatchingStatus(String matchingStatus) {this.machingStatus = matchingStatus;} 
	
	public LocalDateTime getCreatedAt() {return createdAt;} 
	public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
}
