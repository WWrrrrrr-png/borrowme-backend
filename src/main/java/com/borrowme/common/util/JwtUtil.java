package com.borrowme.common.util;



import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;



@Component
public class JwtUtil {
	
	private final SecretKey key; 
	private final long expiration;  
	
	public JwtUtil(@Value("${jwt.secret}") String secret, 
				   @Value("${jwt.expiration}") Long expiration ) {
		
		this.key = Keys.hmacShaKeyFor(secret.getBytes()); 
		this.expiration = expiration;
	}  
	
	public String generateToken(Long id, String role) {
		Date now = new Date(); 
		Date expiry = new Date(now.getTime() + expiration); 
		
		return Jwts.builder()
				.subject(String.valueOf(id))
				.claim("role", role)	
				.issuedAt(now)         
				.expiration(expiry) 
				.signWith(key)
				.compact();
	}

	public Claims parseToken(String token) {
		
		return Jwts.parser()
				.verifyWith(key) 
				.build() 
				.parseSignedClaims(token) 
				.getPayload();
				
	} 
	
	public Long getId(String token) {
		
		return Long.parseLong(parseToken(token).getSubject());
	}

	public String getRole(String token) { 
		
		return parseToken(token).get("role", String.class);
	}

}
