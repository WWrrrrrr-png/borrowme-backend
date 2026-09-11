package com.borrowme.Kakaopayment;

import org.apache.ibatis.annotations.Param;

import com.borrowme.Kakaopayment.dto.PaymentDto;
import com.borrowme.Kakaopayment.dto.PaymentMatchingDto;

public interface PaymentMapper {

    PaymentMatchingDto findMatchingById(@Param("id") Long id);

    void insert(PaymentDto paymentDto);

    void updatePaymentKey(@Param("id") Long id, @Param("paymentKey") String paymentKey);

    void updateStatus(@Param("id") Long id, @Param("status") String status);

    PaymentDto findById(@Param("id") Long id);
}