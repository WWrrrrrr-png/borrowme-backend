package com.borrowme.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.borrowme.admin.dto.AdminHelperDto;
import com.borrowme.admin.dto.AdminLogDto;
import com.borrowme.admin.dto.AdminMatchingViewDto;
import com.borrowme.admin.dto.AdminRequestDto;
import com.borrowme.admin.dto.AdminUserDto;

public interface AdminMapper {
     
	List<AdminUserDto> findAllUsers(); 
	
	AdminUserDto findUserById(@Param("id") Long id); 
	
	void updateUserStatus(@Param("id") Long id,@Param("status") String status); 
	
	List<AdminHelperDto> findAllHelpers();
	
	List<AdminRequestDto> findRequestByStatus(@Param("status") String status); 
	
	AdminRequestDto findRequestById(@Param("id") Long id); 
	
	void updateRequestStatus(@Param("id") Long id,
			                 @Param("status") String status,                    
			                 @Param("adminComment") String adminComment);
	
	void insertAdminLog(AdminLogDto logDto); 
	
	List<AdminMatchingViewDto> findAllMatchingsForMonitor(); 
	
	void updateAmount(@Param("id") Long id, @Param("amount") Integer amount);
}
