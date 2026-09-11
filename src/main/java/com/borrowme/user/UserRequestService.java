package com.borrowme.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.borrowme.common.CustomException;
import com.borrowme.common.ErrorCode;
import com.borrowme.user.dto.UserRequestDto;
import com.borrowme.user.request.RequestCreatedRequest;
import com.borrowme.user.request.RequestUpdateRequest;
import com.borrowme.user.response.RequestResponse;

import lombok.extern.slf4j.Slf4j;



@Service
public class UserRequestService {
	

	private final UserRequestMapper userRequestMapper; 
	
	
	public UserRequestService(UserRequestMapper userRequestMapper) {
		
		this.userRequestMapper = userRequestMapper;
	}
	
	public RequestResponse createRequest(Long userId, RequestCreatedRequest request) {
		
		UserRequestDto dto = new UserRequestDto();  
		
		dto.setUserId(userId);
		
		dto.setTitle(request.getTitle()); 
		
		dto.setContent(request.getContent()); 
		
		userRequestMapper.insert(dto); 
		
		dto.setStatus("PENDING"); 
		
		return RequestResponse.from(dto);
	
	}  
	
	public List<RequestResponse> getMyRequests(Long userId) {
		
		List<UserRequestDto> dtoList = userRequestMapper.findByUserId(userId); 
		
		List<RequestResponse> responseList = new ArrayList<>(); 
		
		for (UserRequestDto dto : dtoList) {
			
			responseList.add(RequestResponse.from(dto) );
		}
	
		return responseList;
	}
	
	
	public RequestResponse getRequestDetail(Long userId, Long requestId) {
		
		
		UserRequestDto dto = findRequestOrThrow(requestId);
		
		checkOwner(dto, userId); 
		
		return RequestResponse.from(dto);
	}
	
	
	public RequestResponse updateRequest(Long userId, Long requestId, RequestUpdateRequest request) {
		
		UserRequestDto dto = findRequestOrThrow(requestId); 
		
		checkOwner(dto, userId);  
		
		dto.setTitle(request.getTitle()); 
		
		dto.setContent(request.getContent());  
		
		userRequestMapper.update(dto); 
		
		return RequestResponse.from(dto);
		
	}  
	
	
	
	public void deleteRequest(Long userId, Long requestId) {
		
		UserRequestDto dto = findRequestOrThrow(requestId); 
		
		checkOwner(dto, userId);  
		
		userRequestMapper.deleteById(requestId);
	}

	
	private UserRequestDto findRequestOrThrow(Long requestId) {
		
		UserRequestDto dto = userRequestMapper.findById(requestId); 
		
		if(dto == null) {
			
			throw new CustomException(ErrorCode.REQUEST_NOT_FOUND);
		}
	
		return dto;
		
	}
	
  private void checkOwner(UserRequestDto dto, Long userId) {
	  
	  if(!dto.getUserId().equals(userId)) {
		  
		  throw new CustomException(ErrorCode.ACCESS_DENIED);
	  }
  }
	
	
}
