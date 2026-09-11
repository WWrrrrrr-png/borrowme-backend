package com.borrowme.admin;

import java.util.List;
import java.util.stream.Collectors;

import com.borrowme.common.CustomException;
import com.borrowme.common.ErrorCode;

import org.springframework.stereotype.Service;

import com.borrowme.admin.dto.AdminHelperDto;
import com.borrowme.admin.dto.AdminLogDto;
import com.borrowme.admin.dto.AdminMatchingViewDto;
import com.borrowme.admin.dto.AdminRequestDto;
import com.borrowme.admin.dto.AdminUserDto;
import com.borrowme.admin.request.RejectRequest;
import com.borrowme.admin.request.StatusUpdateRequest;
import com.borrowme.admin.response.AdminRequestResponse;
import com.borrowme.admin.response.MatchingMonitorResponse;
import com.borrowme.admin.response.MemberSummaryResponse;

@Service
public class AdminService {

	private final AdminMapper adminMapper;

	public AdminService(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}

	public List<MemberSummaryResponse> getAllUsers() {

		List<AdminUserDto> dtoList = adminMapper.findAllUsers();

		return dtoList.stream()
				.map(d -> new MemberSummaryResponse(d.getId(), d.getEmail(), d.getName(), d.getStatus()))
				.collect(Collectors.toList());
	}

	public List<MemberSummaryResponse> getAllHelpers() {
		List<AdminHelperDto> dtoList = adminMapper.findAllHelpers();
		return dtoList.stream()
				.map(d -> new MemberSummaryResponse(d.getId(), d.getEmail(), d.getName(), d.getStatus()))
				.collect(Collectors.toList());
	}

	public MemberSummaryResponse updateUserStatus(Long userId, StatusUpdateRequest request) {

		AdminUserDto dto = adminMapper.findUserById(userId);

		if (dto == null) {
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}
		adminMapper.updateUserStatus(userId, request.getStatus());

		return new MemberSummaryResponse(dto.getId(), dto.getEmail(), dto.getName(), request.getStatus());
	}

	public List<AdminRequestResponse> getRequestByStatus(String status) {

		List<AdminRequestDto> dtoList = adminMapper.findRequestByStatus(status);

		return dtoList.stream()
				.map(AdminRequestResponse::from)
				.collect(Collectors.toList());
	}

	public AdminRequestResponse approveRequest(Long adminId, Long requestId) {

		AdminRequestDto dto = findRequestOrThrow(requestId);

		adminMapper.updateRequestStatus(requestId, "APPROVED", null);
		insertLog(adminId, requestId, "APPROVE", null);

		dto.setStatus("APPROVED");
		return AdminRequestResponse.from(dto);
	}

	public AdminRequestResponse rejectRequest(Long adminId, Long requestId, RejectRequest request) {

		AdminRequestDto dto = findRequestOrThrow(requestId);

		adminMapper.updateRequestStatus(requestId, "REJECTED", request.getReason());
		insertLog(adminId, requestId, "REJECT", request.getReason());

		dto.setStatus("REJECTED");
		dto.setAdminComment(request.getReason());
		return AdminRequestResponse.from(dto);
	}

	public List<MatchingMonitorResponse> getMatchingMonitor() {
		List<AdminMatchingViewDto> dtoList = adminMapper.findAllMatchingsForMonitor();
		return dtoList.stream()
				.map(MatchingMonitorResponse::from)
				.collect(Collectors.toList());
	}

	
	public AdminRequestResponse setRequestAmount(Long requestId, Integer amount) {

		if (amount == null || amount <= 0) {
			throw new CustomException(ErrorCode.INVALID_INPUT);
		}

		AdminRequestDto dto = findRequestOrThrow(requestId);

		adminMapper.updateAmount(requestId, amount);
		dto.setAmount(amount);

		return AdminRequestResponse.from(dto);
	}

	private AdminRequestDto findRequestOrThrow(Long requestId) {

		AdminRequestDto dto = adminMapper.findRequestById(requestId);
		if (dto == null) {
			throw new CustomException(ErrorCode.REQUEST_NOT_FOUND);
		}

		return dto;
	}

	private void insertLog(Long adminId, Long requestId, String action, String reason) {
		AdminLogDto logDto = new AdminLogDto();
		logDto.setAdminId(adminId);
		logDto.setTargetRequestId(requestId);
		logDto.setAction(action);
		logDto.setReason(reason);
		adminMapper.insertAdminLog(logDto);
	}
}