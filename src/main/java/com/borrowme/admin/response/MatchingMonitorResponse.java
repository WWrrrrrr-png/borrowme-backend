package com.borrowme.admin.response;

import java.time.LocalDateTime;

import com.borrowme.admin.dto.AdminMatchingViewDto;

public class MatchingMonitorResponse {
			
	private Long matchingId; 
	private String requestTitle; 
	private String helperName; 
	private String matchingStatus; 
	private LocalDateTime createdAt; 
	
	public MatchingMonitorResponse(Long matchingId,String requestTitle, String helperName,
								  String matchingStatus, LocalDateTime createdAt) {
		
		this.matchingId = matchingId; 
		this.requestTitle = requestTitle; 
		this.helperName = helperName; 
		this.matchingStatus = matchingStatus; 
		this.createdAt = createdAt; 
		
	}
	
	
	public static MatchingMonitorResponse from(AdminMatchingViewDto dto) {
		 
		return new MatchingMonitorResponse(
				dto.getMatchingId(),dto.getRequestTitle(),dto.getHelperName(),
				dto.getMachingStatus(), dto.getCreatedAt()
				
				);
	}  
	
	public Long getMatchingId() {return matchingId;} 
	public String getRequestTitle() {return requestTitle;} 
	public String getHelperName() {return helperName;} 
	public String getMachingStatus() {return matchingStatus;} 
	public LocalDateTime getCreatedAt() {return createdAt;}
	
	
}
