package com.twoyum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RetryController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "retry")
    public String retry() throws InterruptedException {
        ResponseEntity<String> result=restTemplate.getForEntity("http://delivery-send-mail-service/retry",String.class);
        System.err.println("------------ "+ result + "--------------------");
        return "------- result"+result+"--- " ;
    }
}