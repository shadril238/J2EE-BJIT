package com.shadril.eurekaserverdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerDiscoveryApplication.class, args);
	}

}
