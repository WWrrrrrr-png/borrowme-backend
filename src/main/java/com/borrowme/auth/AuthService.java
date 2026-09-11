package com.borrowme.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.borrowme.admin.response.SignupResponse;
import com.borrowme.auth.dto.AdminDto;
import com.borrowme.auth.dto.HelperDto;
import com.borrowme.auth.dto.UserDto;
import com.borrowme.auth.request.LoginRequest;
import com.borrowme.auth.request.SignupRequest;
import com.borrowme.auth.response.LoginResponse;
import com.borrowme.auth.response.MyInfoResponse;
import com.borrowme.common.CustomException;
import com.borrowme.common.ErrorCode;
import com.borrowme.common.util.JwtUtil;

@Service 
@Transactional
public class AuthService {
   
	private final UserAuthMapper userAuthMapper; 
	private final HelperAuthMapper helperAuthMapper; 
	private final AdminAuthMapper adminAuthMapper; 
	private final JwtUtil jwtUtil;  
	
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	
	public AuthService (UserAuthMapper userAuthMapper, HelperAuthMapper helperAuthMapper,
						AdminAuthMapper authMapper, JwtUtil jwtUtil) {
		this.userAuthMapper = userAuthMapper; 
		this.helperAuthMapper = helperAuthMapper; 
		this.adminAuthMapper = authMapper; 
		this.jwtUtil = jwtUtil;
	}  
	
	public SignupResponse signupUser(SignupRequest request) {
		
		UserDto existUser = userAuthMapper.findByEmail(request.getEmail()); 
		if (existUser != null) {
			
			throw new CustomException(ErrorCode.EMAIL_DUPLICATE);
			
		}
		
		UserDto userDto = new UserDto(); 
		userDto.setEmail(request.getEmail());  
		
		userDto.setPassword(passwordEncoder.encode(request.getPassword())); 
		userDto.setName(request.getName()); 
		userDto.setGender(request.getGender());  
		userDto.setPhone(request.getPhone());     
		
		userAuthMapper.insert(userDto); 
		
		return new SignupResponse(userDto.getId(),userDto.getEmail(),userDto.getName());
		
	}  
	
		public boolean checkUserEmail(String email) {
			UserDto existUser = userAuthMapper.findByEmail(email);
			return existUser == null;
		}

	 
	public LoginResponse loginUser(LoginRequest request) {
		UserDto userDto = userAuthMapper.findByEmail(request.getEmail()); 
		
		if(userDto == null) {
			throw new CustomException(ErrorCode.LOGIN_FAILED);
		}   
		
		if(!passwordEncoder.matches(request.getPassword(), userDto.getPassword())) {
		    throw new CustomException(ErrorCode.LOGIN_FAILED);
		}
		
		
		if ("WITHDRAWN".equals(userDto.getStatus())) {
			throw new CustomException(ErrorCode.LOGIN_FAILED);
		}
		
		String token = jwtUtil.generateToken(userDto.getId(), "USER"); 
		
		return new LoginResponse(token, userDto.getId(), userDto.getName());
	}
	
	
	public void deleteUser(Long userId, String password) {

		UserDto userDto = userAuthMapper.findById(userId);
		if (userDto == null) {
			throw new CustomException(ErrorCode.USER_NOT_FOUND);
		}

		boolean isPasswordCorrect = passwordEncoder.matches(password, userDto.getPassword());
		if (!isPasswordCorrect) {
			throw new CustomException(ErrorCode.LOGIN_FAILED);
		}

		String anonymizedEmail = "withdrawn_" + userId + "@deleted.borrowme.com";
		String randomPassword = passwordEncoder.encode(java.util.UUID.randomUUID().toString());

		userAuthMapper.anonymizeById(userId, anonymizedEmail, randomPassword);
	}
	
		
	
	
		public SignupResponse signupHelper(SignupRequest request) {
			
			HelperDto existHelper = helperAuthMapper.findByEmail(request.getEmail()); 
			
			if(existHelper != null) {
				throw new CustomException(ErrorCode.EMAIL_DUPLICATE);
			}
			
				HelperDto helperDto = new HelperDto(); 
				helperDto.setEmail(request.getEmail()); 
				helperDto.setPassword(passwordEncoder.encode(request.getPassword())); 
				helperDto.setName(request.getName()); 
				helperDto.setGender(request.getGender());   
				helperDto.setPhone(request.getPhone()); 
				
				helperAuthMapper.insert(helperDto);
				
				return new SignupResponse(helperDto.getId(), helperDto.getEmail(), helperDto.getName());
		}  
		
		
		public boolean checkHelperEmail(String email) {
			HelperDto existHelper = helperAuthMapper.findByEmail(email);
			return existHelper == null;
		}
		
	
		
		public LoginResponse loginHelper(LoginRequest request) {
			HelperDto helperDto = helperAuthMapper.findByEmail(request.getEmail());
			if (helperDto == null || !passwordEncoder.matches(request.getPassword(), helperDto.getPassword())) {
				throw new CustomException(ErrorCode.LOGIN_FAILED);
			}
			
			if ("WITHDRAWN".equals(helperDto.getStatus())) {
				throw new CustomException(ErrorCode.LOGIN_FAILED);
			}
			String token = jwtUtil.generateToken(helperDto.getId(), "HELPER");
			return new LoginResponse(token, helperDto.getId(), helperDto.getName());
		} 
		
	
	
		
  
	public void deleteHelper(Long helperId, String password) {

		HelperDto helperDto = helperAuthMapper.findById(helperId);
		if (helperDto == null) {
			throw new CustomException(ErrorCode.HELPER_NOT_FOUND);
		}

		boolean isPasswordCorrect = passwordEncoder.matches(password, helperDto.getPassword());
		if (!isPasswordCorrect) {
			throw new CustomException(ErrorCode.LOGIN_FAILED);
		}

		String anonymizedEmail = "withdrawn_" + helperId + "@deleted.borrowme.com";
		String randomPassword = passwordEncoder.encode(java.util.UUID.randomUUID().toString());

		helperAuthMapper.anonymizeById(helperId, anonymizedEmail, randomPassword);
	}
	
	
	
	
		public SignupResponse signupAdmin(SignupRequest request) {
			
			AdminDto existAdmin = adminAuthMapper.findByEmail(request.getEmail()); 
			
			if(existAdmin != null) {
				throw new CustomException(ErrorCode.EMAIL_DUPLICATE);
			}
			
			AdminDto adminDto = new AdminDto(); 
			adminDto.setEmail(request.getEmail()); 
			adminDto.setPassword(passwordEncoder.encode(request.getPassword())); 
			adminDto.setName(request.getName()); 
			
			adminAuthMapper.insert(adminDto); 
			
			return new SignupResponse(adminDto.getId(), adminDto.getEmail(), adminDto.getName());
		}
		
		
	

	public LoginResponse loginAdmin(LoginRequest request) {
		
		AdminDto adminDto = adminAuthMapper.findByEmail(request.getEmail()); 
		
		
		if(adminDto == null) {
			
			throw new CustomException(ErrorCode.LOGIN_FAILED);
		}  
		
		
		boolean isPasswordCorrect = passwordEncoder.matches(request.getPassword(), adminDto.getPassword()); 
		
		
		if(!isPasswordCorrect) {
			throw new CustomException(ErrorCode.LOGIN_FAILED);
		}  
		
		String token = jwtUtil.generateToken(adminDto.getId(), "ADMIN"); 
		
		return new LoginResponse(token, adminDto.getId(), adminDto.getName()); 	
	}    
	
	
	
	public MyInfoResponse getMyInfo(Long id, String role) {
		
		if("USER".equals(role)) {
			UserDto userDto = userAuthMapper.findById(id);
		
			if(userDto == null) {
				
				throw new CustomException(ErrorCode.USER_NOT_FOUND);
			}
		
			return new MyInfoResponse(userDto.getId(), userDto.getEmail(),userDto.getName(), "USER");
		}  
		
		
		if("HELPER".equals(role)) {
			HelperDto helperDto = helperAuthMapper.findById(id); 
			
			if(helperDto == null) {
				throw new CustomException(ErrorCode.HELPER_NOT_FOUND);
			} 
			
			return new MyInfoResponse(helperDto.getId(), helperDto.getEmail(), helperDto.getName(), "HELPER");
			
		}
		 	
		
		if("ADMIN".equals(role)) {
			
			AdminDto adminDto = adminAuthMapper.findById(id); 
			
		if(adminDto == null) {
			throw new CustomException(ErrorCode.ACCESS_DENIED);
		}
			
			return new MyInfoResponse(adminDto.getId(), adminDto.getEmail(), adminDto.getName(), "ADMIN"); 
		 }
	
		 throw new CustomException(ErrorCode.ACCESS_DENIED);
	}
}
