package com.borrowme.Kakaopayment;

import org.springframework.web.bind.annotation.*;

import com.borrowme.Kakaopayment.request.PaymentReadyRequest;
import com.borrowme.Kakaopayment.response.PaymentReadyResponse;
import com.borrowme.Kakaopayment.response.PaymentResponse;
import com.borrowme.common.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/ready")
    public ApiResponse<?> readyPayment(@RequestBody PaymentReadyRequest request, HttpServletRequest httpRequest) {
        Long userId = (Long) httpRequest.getAttribute("memberId");
        PaymentReadyResponse response = paymentService.readyPayment(userId, request);
        return ApiResponse.success(response);
    }

    @GetMapping("/approve")
    public ApiResponse<?> approvePayment(@RequestParam Long paymentId, @RequestParam String pg_token) {
        PaymentResponse response = paymentService.approvePayment(paymentId, pg_token);
        return ApiResponse.success(response);
    }

    @GetMapping("/cancel")
    public ApiResponse<?> cancelPayment(@RequestParam Long paymentId) {
        return ApiResponse.fail("결제가 취소되었습니다.");
    }

    @GetMapping("/fail")
    public ApiResponse<?> failPayment(@RequestParam Long paymentId) {
        return ApiResponse.fail("결제에 실패했습니다.");
    }

    @GetMapping("/{id}")
    public ApiResponse<?> getPaymentDetail(@PathVariable Long id) {
        PaymentResponse response = paymentService.getPaymentDetail(id);
        return ApiResponse.success(response);
    }
}