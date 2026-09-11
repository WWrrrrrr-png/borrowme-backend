package com.borrowme.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.borrowme.admin.request.AdminAmountRequest;
import com.borrowme.admin.request.RejectRequest;
import com.borrowme.admin.request.StatusUpdateRequest;
import com.borrowme.common.ApiResponse;
import com.borrowme.common.CustomException;
import com.borrowme.common.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	
	private Long requireAdmin(HttpServletRequest httpRequest) {
		String role = (String) httpRequest.getAttribute("memberRole");
		if (!"ADMIN".equals(role)) {
			throw new CustomException(ErrorCode.ACCESS_DENIED);
		}
		return (Long) httpRequest.getAttribute("memberId");
	}

	@GetMapping("/users")
	public ApiResponse<?> getAllUsers(HttpServletRequest httpRequest) {
		requireAdmin(httpRequest);
		return ApiResponse.success(adminService.getAllUsers());
	}

	@GetMapping("/helpers")
	public ApiResponse<?> getAllHelpers(HttpServletRequest httpRequest) {
		requireAdmin(httpRequest);
		return ApiResponse.success(adminService.getAllHelpers());
	}

	@PatchMapping("/users/{id}/status")
	public ApiResponse<?> updateUserStatus(@PathVariable Long id,
											 @RequestBody StatusUpdateRequest request,
											 HttpServletRequest httpRequest) {
		requireAdmin(httpRequest);
		return ApiResponse.success(adminService.updateUserStatus(id, request));
	}

	@GetMapping("/requests")
	public ApiResponse<?> getRequests(@RequestParam(defaultValue = "PENDING") String status,
										HttpServletRequest httpRequest) {
		requireAdmin(httpRequest);
		return ApiResponse.success(adminService.getRequestByStatus(status));
	}

	
	@PatchMapping("/requests/{id}/approve")
	public ApiResponse<?> approveRequest(@PathVariable Long id, HttpServletRequest httpRequest) {
		Long adminId = requireAdmin(httpRequest);
		return ApiResponse.success(adminService.approveRequest(adminId, id));
	}

	
	@PatchMapping("/requests/{id}/reject")
	public ApiResponse<?> rejectRequest(@PathVariable Long id,
										 @RequestBody RejectRequest request,
										 HttpServletRequest httpRequest) {
		Long adminId = requireAdmin(httpRequest);
		return ApiResponse.success(adminService.rejectRequest(adminId, id, request));
	}

	@GetMapping("/matchings")
	public ApiResponse<?> getMatchingMonitor(HttpServletRequest httpRequest) {
		requireAdmin(httpRequest);
		return ApiResponse.success(adminService.getMatchingMonitor());
	}  
	
	@PatchMapping("/requests/{id}/amount")
	public ApiResponse<?> setRequestAmount(@PathVariable Long id, @RequestBody AdminAmountRequest request,
											HttpServletRequest httpRequest) {
		requireAdmin(httpRequest);
		return ApiResponse.success(adminService.setRequestAmount(id, request.getAmount()));
	}
}