package com.borrowme.borrowme.order.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowMeOrderDto {
	private Long id;             
    private Long userId;         
    private String tid;          
    private String itemName;     
    private Integer price;       
    private String status;       
    private LocalDateTime createdAt;
}
