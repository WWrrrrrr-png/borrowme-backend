package com.borrowme.auth;

import org.apache.ibatis.annotations.Param;

import com.borrowme.auth.dto.UserDto;

public interface UserAuthMapper {
   
	UserDto findByEmail(@Param("email") String email); 
	
	UserDto findById(@Param("id") Long id); 
	
	void insert(UserDto userDto); 
	
	void anonymizeById(@Param("id") Long id, @Param("email") String email,
			@Param("password") String password);
}
