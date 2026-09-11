package com.borrowme.helper.response;

import java.time.LocalDateTime;

import com.borrowme.helper.dto.HelperRequestDto;


public class HelperRequestResponse {

 private Long id;
 private String title;
 private String content;
 private LocalDateTime createdAt;

 public HelperRequestResponse(Long id, String title, String content, LocalDateTime createdAt) {
     this.id = id;
     this.title = title;
     this.content = content;
     this.createdAt = createdAt;
 }

 public static HelperRequestResponse from(HelperRequestDto dto) {
     return new HelperRequestResponse(dto.getId(), dto.getTitle(), dto.getContent(), dto.getCreatedAt());
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

 public LocalDateTime getCreatedAt() {
     return createdAt;
 }
}
