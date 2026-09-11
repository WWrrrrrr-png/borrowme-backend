package com.borrowme.common.fliter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.borrowme.common.util.JwtUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtAuthFilter implements Filter {
  
	private final JwtUtil jwtUtil; 
	
	public JwtAuthFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request; 
		
		String header = httpRequest.getHeader("Authorization"); 
		
		if (header != null && header.startsWith("Bearer ")) { 
		    String token = header.substring(7).trim(); 
		    try { 
		        httpRequest.setAttribute("memberId", jwtUtil.getId(token)); 
		        httpRequest.setAttribute("memberRole", jwtUtil.getRole(token));
		    } catch (Exception e) {
		        System.err.println("JWT 토큰 검증 실패: " + e.getMessage()); 
		    }
		}
		
		chain.doFilter(request, response);
	}  
}