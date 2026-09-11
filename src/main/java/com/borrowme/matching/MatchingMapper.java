package com.borrowme.matching;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.borrowme.matching.dto.MatchingDto;

public interface MatchingMapper {


    MatchingDto findById(@Param("id") Long id);
   
    void updateStatus(@Param("id") Long id, @Param("status") String status); 
	
    List<MatchingDto> findByHelperId(@Param("helperId") Long helperId);
}