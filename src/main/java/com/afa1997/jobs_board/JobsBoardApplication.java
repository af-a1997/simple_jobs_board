package com.afa1997.jobs_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JobsBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsBoardApplication.class, args);
	}

}
