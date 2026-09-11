package com.borrowme.Kakaopayment.response;

import java.time.LocalDateTime;

import com.borrowme.Kakaopayment.dto.PaymentDto;

public class PaymentResponse {

    private Long id;
    private Long matchingId;
    private Integer amount;
    private String paymentStatus;
    private String paymentMethod;
    private LocalDateTime createdAt;

    public PaymentResponse(Long id, Long matchingId, Integer amount, String paymentStatus,
                            String paymentMethod, LocalDateTime createdAt) {
        this.id = id;
        this.matchingId = matchingId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
    }

    public static PaymentResponse from(PaymentDto dto) {
        return new PaymentResponse(
                dto.getId(), dto.getMatchingId(), dto.getAmount(),
                dto.getPaymentStatus(), dto.getPaymentMethod(), dto.getCreatedAt()
        );
    }

    public Long getId() { return id; }
    public Long getMatchingId() { return matchingId; }
    public Integer getAmount() { return amount; }
    public String getPaymentStatus() { return paymentStatus; }
    public String getPaymentMethod() { return paymentMethod; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}