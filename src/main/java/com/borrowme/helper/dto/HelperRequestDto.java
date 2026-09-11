package com.borrowme.helper.dto;

import java.time.LocalDateTime;


public class HelperRequestDto {

 private Long id;
 private Long userId;
 private String title;
 private String content;
 private String status;
 private LocalDateTime createdAt;

 public HelperRequestDto() {
 }

 public Long getId() {
     return id;
 }

 public void setId(Long id) {
     this.id = id;
 }

 public Long getUserId() {
     return userId;
 }

 public void setUserId(Long userId) {
     this.userId = userId;
 }

 public String getTitle() {
     return title;
 }

 public void setTitle(String title) {
     this.title = title;
 }

 public String getContent() {
     return content;
 }

 public void setContent(String content) {
     this.content = content;
 }

 public String getStatus() {
     return status;
 }

 public void setStatus(String status) {
     this.status = status;
 }

 public LocalDateTime getCreatedAt() {
     return createdAt;
 }

 public void setCreatedAt(LocalDateTime createdAt) {
     this.createdAt = createdAt;
 }
}