package com.borrowme.auth;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.borrowme.admin.response.SignupResponse;
import com.borrowme.auth.request.DeleteAccountRequest;
import com.borrowme.auth.request.LoginRequest;
import com.borrowme.auth.request.SignupRequest;
import com.borrowme.auth.response.LoginResponse;
import com.borrowme.auth.response.MyInfoResponse;
import com.borrowme.common.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")   
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	
	@PostMapping("/user/signup")
	public ApiResponse<?> signUser(@RequestBody SignupRequest request) {
		SignupResponse response = authService.signupUser(request);
		return ApiResponse.success(response);
	}
	
	
	@GetMapping("/user/check-email")
	public ApiResponse<?> checkUserEmail(@RequestParam String email) {
		boolean isAvailable = authService.checkUserEmail(email);
		return ApiResponse.success(isAvailable);
	}

	@PostMapping("/user/login")
	public ApiResponse<?> userLogin(@RequestBody LoginRequest request) {
		LoginResponse response = authService.loginUser(request);
		return ApiResponse.success(response);
	}  
	

	@DeleteMapping("/user/me")
	public ApiResponse<?> deleteUser(@RequestBody DeleteAccountRequest request, HttpServletRequest httpRequest) {
		Long memberId = (Long) httpRequest.getAttribute("memberId");
		authService.deleteUser(memberId, request.getPassword());
		return ApiResponse.success(null);
	}

	@PostMapping("/helper/signup")
	public ApiResponse<?> signupHelper(@RequestBody SignupRequest request) {
		SignupResponse response = authService.signupHelper(request);
		return ApiResponse.success(response);
	}
	
	
	@GetMapping("/helper/check-email")
		public ApiResponse<?> checkHelperEmail(@RequestParam String email) {
			boolean isAvailable = authService.checkHelperEmail(email);
			return ApiResponse.success(isAvailable);
		}	
	
	@DeleteMapping("/helper/me")
	public ApiResponse<?> deleteHelper(@RequestBody DeleteAccountRequest request, HttpServletRequest httpRequest) {
		Long memberId = (Long) httpRequest.getAttribute("memberId");
		authService.deleteHelper(memberId, request.getPassword());
		return ApiResponse.success(null);
	}
	
	@PostMapping("/helper/login")
	public ApiResponse<?> loginHelper(@RequestBody LoginRequest request) {
		LoginResponse response = authService.loginHelper(request);
		return ApiResponse.success(response);
	} 
	
	
	@PostMapping("/admin/login")
	public ApiResponse<?> loginAdmin(@RequestBody LoginRequest request) {
		LoginResponse response = authService.loginAdmin(request);
		return ApiResponse.success(response);
	}

	@GetMapping("/me")
	public ApiResponse<?> getMyInfo(HttpServletRequest httpRequest) {
		Long memberId = (Long) httpRequest.getAttribute("memberId");
		String memberRole = (String) httpRequest.getAttribute("memberRole");

		MyInfoResponse response = authService.getMyInfo(memberId, memberRole);
		return ApiResponse.success(response);
	}
}