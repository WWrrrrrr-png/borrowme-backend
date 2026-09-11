package com.borrowme.common;

public class ApiResponse<T> {

	private boolean success; 
	private T data; 
	private String message; 
	
	private ApiResponse(boolean success, T data, String message) {
		this.success = success; 
		this.data = data;
		this.message = message; 
		
	} 
	
	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<T>(true, data, "요청이 성공 했습니다!");
	}
	
	public static <T> ApiResponse<T> fail(String message) {
		return new ApiResponse<T>(false, null, message);
	} 
	
	public boolean isSuccess() {return success;} 
	public T getData() { return data;} 
	public String getMessage() { return message;}
}
