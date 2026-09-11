package com.borrowme.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionHandler {
   
	@ExceptionHandler(CustomException.class) 
	public ResponseEntity<ApiResponse<Object>> handleCustomException(CustomException e) {
		
		return ResponseEntity 
				.status(500) 
				.body(ApiResponse.fail("서버 내부 오류가 발생했습니다."));
	}
}
