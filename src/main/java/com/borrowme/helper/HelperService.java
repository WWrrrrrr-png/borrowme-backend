package com.borrowme.helper;

import com.borrowme.common.CustomException;
import com.borrowme.common.ErrorCode;
import com.borrowme.helper.dto.HelperMatchingDto;
import com.borrowme.helper.dto.HelperRequestDto;
import com.borrowme.helper.response.HelperRequestResponse;
import com.borrowme.helper.response.HelperMatchingResponse;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HelperService {

    private final HelperMapper helperMapper;

    
    public HelperService(HelperMapper helperMapper) {
        this.helperMapper = helperMapper;
    }

  
    public List<HelperRequestResponse> getApprovedRequests() {

        List<HelperRequestDto> dtoList = helperMapper.findApprovedRequests();

        List<HelperRequestResponse> responseList = new ArrayList<>();
        for (HelperRequestDto dto : dtoList) {
            responseList.add(HelperRequestResponse.from(dto));
        }

        return responseList;
    }

    
    public HelperMatchingResponse acceptRequest(Long helperId, Long requestId) {

        HelperRequestDto requestDto = helperMapper.findRequestById(requestId);

        if (requestDto == null) {
            throw new CustomException(ErrorCode.REQUEST_NOT_FOUND);
        }

       
        if (!"APPROVED".equals(requestDto.getStatus())) {
            throw new CustomException(ErrorCode.REQUEST_NOT_APPROVED);
        }

       
        helperMapper.updateRequestStatus(requestId, "MATCHED");

       
        HelperMatchingDto matchingDto = new HelperMatchingDto();
        matchingDto.setRequestId(requestId);
        matchingDto.setHelperId(helperId);

        helperMapper.insertMatching(matchingDto);

        return new HelperMatchingResponse(matchingDto.getId(), "MATCHED");
    }
}
