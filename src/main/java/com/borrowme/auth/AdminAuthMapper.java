package com.borrowme.auth;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.borrowme.auth.dto.AdminDto;

@Mapper
public interface AdminAuthMapper {
	AdminDto findByEmail(@Param("email") String email); 
	
	AdminDto findById(@Param("id") Long id); 
	
	void insert(AdminDto adminDto);
}
