package com.borrowme.admin.response;

import java.time.LocalDateTime;

import com.borrowme.admin.dto.AdminRequestDto;

public class AdminRequestResponse {

	private Long id;
	private Long userId;
	private String title;
	private String content;
	private Integer amount; 
	private String status;
	private String adminComment; 
	private LocalDateTime createdAt;

	
	public AdminRequestResponse(Long id, Long userId, String title, String content,
			Integer amount, String status, String adminComment, LocalDateTime createdAt) {

		this.id = id;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.amount = amount;
		this.status = status;
		this.adminComment = adminComment;
		this.createdAt = createdAt;
	}

	public static AdminRequestResponse from(AdminRequestDto dto) {

		return new AdminRequestResponse(dto.getId(), dto.getUserId(), dto.getTitle(), dto.getContent(),
				dto.getAmount(), dto.getStatus(), dto.getAdminComment(), dto.getCreatedAt());
	}

	public Long getId() {return id;}
	public Long getUserId() {return userId;}
	public String getTitle() {return title;}
	public String getContent() {return content;}
	public String getStatus() {return status;}
	public Integer getAmount() {return amount;}
	public String getAdminComment() {return adminComment;}
	public LocalDateTime getCreatedAt() {return createdAt;}
}