package com.twoyum.merchant.controller;


import com.twoyum.merchant.domain.LogInfoInDto;
import com.twoyum.merchant.utils.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

public class BaseController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EnumUtil enumUtil;

    public void addLogInfo(String account,HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON_UTF8);
        LogInfoInDto logInfoInDto = new LogInfoInDto();
        logInfoInDto.setOperator(account);
        logInfoInDto.setRequestBusiness(request.getRequestURI());
        logInfoInDto.setRequestMethod(request.getMethod());
        String useAgent = request.getHeader("user-agent");
        logInfoInDto.setChannel(useAgent);
        HttpEntity<LogInfoInDto> entity = new HttpEntity<>(logInfoInDto, headers);
        restTemplate.postForObject("http://DELIVERY-COMMONS-SERVICE/logInfo/add",entity,String.class);
    }
}
