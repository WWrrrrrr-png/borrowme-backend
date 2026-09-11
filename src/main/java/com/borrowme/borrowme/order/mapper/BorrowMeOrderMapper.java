package com.borrowme.borrowme.order.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.borrowme.borrowme.order.dto.BorrowMeOrderDto;


@Mapper
public interface BorrowMeOrderMapper {
	void insertOrder(BorrowMeOrderDto borrowMeorderDto);
}
