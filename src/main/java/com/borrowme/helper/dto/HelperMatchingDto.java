package com.borrowme.helper.dto;

import java.time.LocalDateTime;


public class HelperMatchingDto {

 private Long id;
 private Long requestId; 
 private Long helperId;  
 private String status;  
 private LocalDateTime createdAt;

 public HelperMatchingDto() {
 }

 public Long getId() {
     return id;
 }

 public Long getRequestId() {
     return requestId;
 }

 public Long getHelperId() {
     return helperId;
 } 
 
 public String getStatus() {
     return status;
 }

 public LocalDateTime getCreatedAt() {
     return createdAt;
 }
 
 
 
 public void setId(Long id) {
     this.id = id;
 }

 public void setRequestId(Long requestId) {
     this.requestId = requestId;
 }

 public void setHelperId(Long helperId) {
     this.helperId = helperId;
 }

 public void setStatus(String status) {
     this.status = status;
 }


 public void setCreatedAt(LocalDateTime createdAt) {
     this.createdAt = createdAt;
 }
}
