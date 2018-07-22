package com.twoyum.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(value = "/retry")
    public String retry() throws InterruptedException {
        System.err.println("------- client 02--- ");
        return "------- client 02--- " ;
    }
}