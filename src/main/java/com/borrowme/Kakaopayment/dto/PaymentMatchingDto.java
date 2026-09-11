package com.borrowme.Kakaopayment.dto;

public class PaymentMatchingDto {

    private Long id;
    private String status;
    private Integer amount;   

    public PaymentMatchingDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }
}