package com.borrowme;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.borrowme")
public class BorrowmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BorrowmeApplication.class, args);
	}

}
