package com.ai.ChatBotIntegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ChatBotIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatBotIntegrationApplication.class, args);
	}

}
