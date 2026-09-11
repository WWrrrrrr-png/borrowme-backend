package com.borrowme.borrowme.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.borrowme.borrowme.order.dto.BorrowMeOrderDto;
import com.borrowme.borrowme.order.mapper.BorrowMeOrderMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowMeOrderService {
	private final BorrowMeOrderMapper borrowMeOrderMapper; 
	
	public void BorrowMeSaveOrder(BorrowMeOrderDto orderDto) {
        borrowMeOrderMapper.insertOrder(orderDto);
    }
    
}
