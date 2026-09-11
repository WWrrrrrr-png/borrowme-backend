package com.borrowme.user.request;

  
public class RequestCreatedRequest {
   
	private String title; 
	private String content;  
	
	public RequestCreatedRequest() {}  
	
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
