package com.borrowme.Kakaopayment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.borrowme.Kakaopayment.dto.PaymentDto;
import com.borrowme.Kakaopayment.dto.PaymentMatchingDto;
import com.borrowme.Kakaopayment.request.PaymentReadyRequest;
import com.borrowme.Kakaopayment.response.PaymentReadyResponse;
import com.borrowme.Kakaopayment.response.PaymentResponse;
import com.borrowme.common.CustomException;
import com.borrowme.common.ErrorCode;
import com.borrowme.matching.MatchingMapper;

@Service
public class PaymentService {

    private final PaymentMapper paymentMapper;
    private final MatchingMapper matchingMapper;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${kakaopay.readUrl}")
    private String readUrl;

    @Value("${kakaopay.approveUrl}")
    private String approveUrl;

    @Value("${kakaopay.secretKey}")
    private String secretKey;

    @Value("${kakaopay.cid}")
    private String cid;

    @Value("${kakaopay.approval}")
    private String approvalUrl;

    @Value("${kakaopay.cancel}")
    private String cancelUrl;

    @Value("${kakaopay.fail}")
    private String failUrl;

    public PaymentService(PaymentMapper paymentMapper, MatchingMapper matchingMapper) {
        this.paymentMapper = paymentMapper;
        this.matchingMapper = matchingMapper;
    }

    public PaymentReadyResponse readyPayment(Long userId, PaymentReadyRequest request) {

        PaymentMatchingDto matchingDto = paymentMapper.findMatchingById(request.getMatchingId());
        if (matchingDto == null) {
            throw new CustomException(ErrorCode.MATCHING_NOT_FOUND);
        }

        if (matchingDto.getAmount() == null) {
            throw new CustomException(ErrorCode.AMOUNT_NOT_SET);
        }
        Integer amount = matchingDto.getAmount();

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setMatchingId(request.getMatchingId());
        paymentDto.setUserId(userId);
        paymentDto.setAmount(amount);
        paymentMapper.insert(paymentDto);

        Map<String, Object> body = new HashMap<>();
        body.put("cid", cid);
        body.put("partner_order_id", paymentDto.getId().toString());
        body.put("partner_user_id", userId.toString());
        body.put("item_name", "BorrowMe 서비스 이용료");
        body.put("quantity", 1);
        body.put("total_amount", amount);
        body.put("tax_free_amount", 0);
        body.put("approval_url", approvalUrl + "?paymentId=" + paymentDto.getId());
        body.put("cancel_url", cancelUrl + "?paymentId=" + paymentDto.getId());
        body.put("fail_url", failUrl + "?paymentId=" + paymentDto.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "SECRET_KEY " + secretKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(body, headers);

        Map<String, Object> response;
        try {
            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(readUrl, httpEntity, Map.class);
            response = responseEntity.getBody();
        } catch (Exception e) {
            System.out.println("=== 카카오페이 ready 요청 실패 상세 원인 ===");
            e.printStackTrace();
            throw new CustomException(ErrorCode.PAYMENT_FAILED);
        }

        if (response == null) {
            throw new CustomException(ErrorCode.PAYMENT_FAILED);
        }

        String tid = (String) response.get("tid");
        String redirectUrl = (String) response.get("next_redirect_pc_url");

        paymentMapper.updatePaymentKey(paymentDto.getId(), tid);

        return new PaymentReadyResponse(paymentDto.getId(), redirectUrl);
    }

    public PaymentResponse approvePayment(Long paymentId, String pgToken) {

        PaymentDto paymentDto = paymentMapper.findById(paymentId);
        if (paymentDto == null) {
            throw new CustomException(ErrorCode.PAYMENT_NOT_FOUND);
        }

       
        if ("SUCCESS".equals(paymentDto.getPaymentStatus())) {
            return PaymentResponse.from(paymentDto);
        }

        Map<String, Object> body = new HashMap<>();
        body.put("cid", cid);
        body.put("tid", paymentDto.getPaymentKey());
        body.put("partner_order_id", paymentDto.getId().toString());
        body.put("partner_user_id", paymentDto.getUserId().toString());
        body.put("pg_token", pgToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "SECRET_KEY " + secretKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> responseEntity;
        try {
            responseEntity = restTemplate.postForEntity(approveUrl, httpEntity, Map.class);
        } catch (Exception e) {
            System.out.println("=== 카카오페이 approve 요청 실패 상세 원인 ===");
            e.printStackTrace();
            throw new CustomException(ErrorCode.PAYMENT_FAILED);
        }

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new CustomException(ErrorCode.PAYMENT_FAILED);
        }

        paymentMapper.updateStatus(paymentId, "SUCCESS");
        paymentDto.setPaymentStatus("SUCCESS");

     
        matchingMapper.updateStatus(paymentDto.getMatchingId(), "COMPLETED");

        return PaymentResponse.from(paymentDto);
    }

    public PaymentResponse getPaymentDetail(Long paymentId) {
        PaymentDto paymentDto = paymentMapper.findById(paymentId);
        if (paymentDto == null) {
            throw new CustomException(ErrorCode.PAYMENT_NOT_FOUND);
        }
        return PaymentResponse.from(paymentDto);
    }
}