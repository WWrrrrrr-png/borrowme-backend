package com.borrowme.auth;

import org.apache.ibatis.annotations.Param;

import com.borrowme.auth.dto.HelperDto;

public interface HelperAuthMapper {
		
	HelperDto findByEmail(@Param("email") String email); 
	
	HelperDto findById(@Param("id") Long id); 
	
	void insert(HelperDto helperDto); 
	
	void anonymizeById(@Param("id") Long id, @Param("email") String email,
			@Param("password") String password);
	
}
