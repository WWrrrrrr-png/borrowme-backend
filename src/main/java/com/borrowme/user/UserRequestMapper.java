package com.borrowme.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.borrowme.user.dto.UserRequestDto;

public interface UserRequestMapper {
	
	
	void insert(UserRequestDto requestDto); 
	
	
	UserRequestDto findById(@Param("id") Long id);  
	
	
	List<UserRequestDto> findByUserId( @Param("userId") Long userId); 
	
	
	void update(UserRequestDto userRequestDto);
	
	
	void deleteById(@Param("id") Long id);
}
