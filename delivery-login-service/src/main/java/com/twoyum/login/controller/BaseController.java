package com.twoyum.login.controller;

import com.twoyum.login.domain.LogInfoInDto;
import com.twoyum.login.domain.SendMailInDto;
import com.twoyum.login.utils.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import java.net.URLEncoder;

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

    public void sendTemplateMail(String from,String email,String resetPassHref,String typeCode){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON_UTF8);
        SendMailInDto sendMailInDto = new SendMailInDto();
        sendMailInDto.setFrom(from);
        sendMailInDto.setTo(email);
        sendMailInDto.setSubject(enumUtil.getTipMsg(typeCode,16));
        sendMailInDto.setText(enumUtil.getTipMsg(typeCode,13));
        sendMailInDto.setBtnText(enumUtil.getTipMsg(typeCode,17));
        sendMailInDto.setUrl(resetPassHref);
        HttpEntity<SendMailInDto> entity = new HttpEntity<>(sendMailInDto, headers);
        restTemplate.postForObject("http://DELIVERY-SEND-MAIL-RIBBON-SERVICE/sendMailConsumer/sendTemplateMail", entity, String.class);

    }
}
