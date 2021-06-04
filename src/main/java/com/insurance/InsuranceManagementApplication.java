package com.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class InsuranceManagementApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(InsuranceManagementApplication.class, args);
		log.info("Online Insurance App Started in {} Environment",
				  context.getEnvironment().getProperty("environment"));
	}
}
