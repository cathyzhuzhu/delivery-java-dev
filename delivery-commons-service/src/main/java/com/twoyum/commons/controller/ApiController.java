package com.twoyum.commons.controller;

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
