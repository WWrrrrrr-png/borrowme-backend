package com.borrowme.Kakaopayment.response;

public class PaymentReadyResponse {

    private Long paymentId;
    private String redirectUrl;

    public PaymentReadyResponse(Long paymentId, String redirectUrl) {
        this.paymentId = paymentId;
        this.redirectUrl = redirectUrl;
    }

    public Long getPaymentId() { return paymentId; }
    public String getRedirectUrl() { return redirectUrl; }
}