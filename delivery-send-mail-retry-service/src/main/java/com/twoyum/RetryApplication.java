package com.twoyum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableRetry
@EnableDiscoveryClient
public class RetryApplication {

	@Bean
	@ConfigurationProperties(prefix = "custom.rest")
	public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(){
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory=new HttpComponentsClientHttpRequestFactory();

		return httpComponentsClientHttpRequestFactory;
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate(httpComponentsClientHttpRequestFactory());
	}

	public static void main(String[] args) {
		SpringApplication.run(RetryApplication.class,  args);
	}
}
