package com.borrowme.helper;

import org.apache.ibatis.annotations.Param;

import com.borrowme.helper.dto.HelperMatchingDto;
import com.borrowme.helper.dto.HelperRequestDto;

import java.util.List;

public interface HelperMapper {

    
    List<HelperRequestDto> findApprovedRequests();

    
    HelperRequestDto findRequestById(@Param("id") Long id);

    
    void updateRequestStatus(@Param("id") Long id, @Param("status") String status);

  
    void insertMatching(HelperMatchingDto matchingDto);
}