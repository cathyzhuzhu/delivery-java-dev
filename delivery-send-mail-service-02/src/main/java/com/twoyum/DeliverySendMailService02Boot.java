package com.twoyum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DeliverySendMailService02Boot {

	public static void main(String[] args) {
		SpringApplication.run(DeliverySendMailService02Boot.class, args);
	}

}
