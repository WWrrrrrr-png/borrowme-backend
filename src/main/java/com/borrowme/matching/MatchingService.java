package com.borrowme.matching;

import com.borrowme.common.CustomException;
import com.borrowme.common.ErrorCode;
import com.borrowme.matching.dto.MatchingDto;
import com.borrowme.matching.request.MatchingStatusUpdateRequest;
import com.borrowme.matching.response.MatchingResponse;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class MatchingService {

	private final MatchingMapper matchingMapper;

	public MatchingService(MatchingMapper matchingMapper) {
		this.matchingMapper = matchingMapper;
	}


	public MatchingResponse getMatchingDetail(Long matchingId) {

		MatchingDto dto = matchingMapper.findById(matchingId);

		if (dto == null) {
			throw new CustomException(ErrorCode.MATCHING_NOT_FOUND);
		}

		return MatchingResponse.from(dto);
	}

	
	public MatchingResponse updateMatchingStatus(Long matchingId, MatchingStatusUpdateRequest request) {

		MatchingDto dto = matchingMapper.findById(matchingId);

		if (dto == null) {
			throw new CustomException(ErrorCode.MATCHING_NOT_FOUND);
		}

		String newStatus = request.getStatus();

		
		boolean isValidStatus = "IN_PROGRESS".equals(newStatus) || "COMPLETED".equals(newStatus);
		if (!isValidStatus) {
			throw new CustomException(ErrorCode.INVALID_INPUT);
		}

		matchingMapper.updateStatus(matchingId, newStatus);

	
		dto.setStatus(newStatus);

		return MatchingResponse.from(dto);
	}

	
	public List<MatchingResponse> getMyMatchings(Long helperId) {
		List<MatchingDto> list = matchingMapper.findByHelperId(helperId);
		return list.stream()
				.map(MatchingResponse::from)
				.collect(Collectors.toList());
	}
}