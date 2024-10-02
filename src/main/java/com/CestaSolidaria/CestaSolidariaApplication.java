package com.CestaSolidaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CestaSolidariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CestaSolidariaApplication.class, args);
	}
}
