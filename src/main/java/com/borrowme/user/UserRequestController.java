package com.borrowme.user;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.borrowme.common.ApiResponse;
import com.borrowme.user.request.RequestCreatedRequest;
import com.borrowme.user.request.RequestUpdateRequest;
import com.borrowme.user.response.RequestResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController 
@RequestMapping("/api/requests")
public class UserRequestController {
		 
	  private final UserRequestService userRequestService; 
	  
	  public UserRequestController(UserRequestService userRequestService) {
		  this.userRequestService = userRequestService; 
	  }  
	  
	  @PostMapping
	  public ApiResponse<?> createRequest(
			  
			  @RequestBody RequestCreatedRequest request, 
			  HttpServletRequest httpRequest ) {
		  
		  Long userId = (Long) httpRequest.getAttribute("memberId");   
		  
		  RequestResponse response = userRequestService.createRequest(userId, request);
		  
		  return ApiResponse.success(response);
	  }
			  
	
	  
	@GetMapping("my") 
	public ApiResponse<?> getMyRequests(HttpServletRequest httpRquest) {
		
		Long userId = (Long) httpRquest.getAttribute("memberId");   
		
		List<RequestResponse> responseList = userRequestService.getMyRequests(userId);
		
		return ApiResponse.success(responseList);
		
	}
			  
	
	
	@GetMapping("/{id}") 
	public ApiResponse<?> getRequestDetail(@PathVariable Long id,
			                               HttpServletRequest httpRequest) {
		
		Long userId = (Long) httpRequest.getAttribute("memberId");  
		
		RequestResponse response = userRequestService.getRequestDetail(userId, id); 
		
		return ApiResponse.success(response);
	}
			
   
	
	@PutMapping("/{id}") 
   public ApiResponse<?> updateRequest (@PathVariable Long id, 
		   								@RequestBody RequestUpdateRequest request,
		   								HttpServletRequest httpRequest) {
	   Long userId = (Long) httpRequest.getAttribute("memberId"); 
	   
	   RequestResponse response = userRequestService.updateRequest(userId, id,request); 
	   
	   return ApiResponse.success(response);
   }
	
	
	@DeleteMapping("/{id}") 
	public ApiResponse<?> deleteRequest(@PathVariable Long id,
										HttpServletRequest httpRequest) {
		Long userId = (Long) httpRequest.getAttribute("memberId");   
		
		userRequestService.deleteRequest(userId, id); 
		
		return ApiResponse.success(null);
	}
}
