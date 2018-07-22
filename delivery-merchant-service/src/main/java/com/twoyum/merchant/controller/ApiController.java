package com.twoyum.merchant.controller;

import com.twoyum.merchant.response.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @RequestMapping("/check")
    public String CheckApi(){
        return "service ok";
    }

}
