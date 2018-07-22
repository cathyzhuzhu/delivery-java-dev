package com.twoyum;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DeliveryEurekaServerBoot {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DeliveryEurekaServerBoot.class).web(true).run(args);
	}

}
