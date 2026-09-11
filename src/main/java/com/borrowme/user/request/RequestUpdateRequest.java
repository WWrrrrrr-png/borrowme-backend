package com.borrowme.user.request;

public class RequestUpdateRequest {
    
	private String title; 
	private String content;  
	
	public RequestUpdateRequest() {
		
	}  
	
	public String getTitle() {
		return title;
	}  
	
	public String getContent() {
		return content;
	}  
	
	public void setTitle(String title) {
		this.title = title;
	} 
	
	public void setContent(String content) {
		this.content = content;
	}
}
