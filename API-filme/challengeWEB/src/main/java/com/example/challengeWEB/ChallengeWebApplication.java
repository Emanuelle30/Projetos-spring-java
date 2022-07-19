package com.example.challengeWEB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChallengeWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeWebApplication.class, args);
	}

}
