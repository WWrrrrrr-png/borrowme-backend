package com.borrowme.Kakaopayment.dto;

import java.time.LocalDateTime;

public class PaymentDto {

    private Long id;
    private Long matchingId;
    private Long userId;
    private Integer amount;
    private String paymentStatus;  
    private String paymentMethod; 
    private String paymentKey;     
    private LocalDateTime createdAt;

    public PaymentDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getMatchingId() { return matchingId; }
    public void setMatchingId(Long matchingId) { this.matchingId = matchingId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getPaymentKey() { return paymentKey; }
    public void setPaymentKey(String paymentKey) { this.paymentKey = paymentKey; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
