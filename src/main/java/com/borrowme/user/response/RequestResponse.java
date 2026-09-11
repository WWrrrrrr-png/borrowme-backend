package com.borrowme.user.response;

import java.time.LocalDateTime;

import com.borrowme.user.dto.UserRequestDto;

public class RequestResponse {

	private Long id;
	private String title;
	private String status;
	private String content;
	private Integer amount;        
	private String adminComment;
	private LocalDateTime createdAt;

	public RequestResponse(Long id, String title, String status,
							String content, Integer amount, String adminComment, LocalDateTime createdAt) {

		this.id = id;
		this.title = title;
		this.status = status;
		this.content = content;
		this.amount = amount;
		this.adminComment = adminComment;
		this.createdAt = createdAt;
	}

	public static RequestResponse from(UserRequestDto dto) {

		return new RequestResponse(
				dto.getId(),
				dto.getTitle(),
				dto.getStatus(),
				dto.getContent(),
				dto.getAmount(),
				dto.getAdminComment(),
				dto.getCreatedAt()
		);
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Integer getAmount() {          
		return amount;
	}

	public String getStatus() {
		return status;
	}

	public String getAdminComment() {
		return adminComment;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}