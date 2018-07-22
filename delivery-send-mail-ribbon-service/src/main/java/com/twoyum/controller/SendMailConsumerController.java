package com.twoyum.controller;

import com.twoyum.domain.SendMailInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@RestController
@RequestMapping("sendMailConsumer")
public class SendMailConsumerController {
        @Autowired
        private RestTemplate restTemplate;

        @RequestMapping(value = "/sendTemplateMail", method = RequestMethod.POST)
        public String sendMailRibbonConsumer(@RequestBody SendMailInDto sendMailInDto) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(APPLICATION_JSON_UTF8);
            HttpEntity<SendMailInDto> entity = new HttpEntity<>(sendMailInDto, headers);
            return restTemplate.postForObject("http://delivery-send-mail-service/sendMail/sendTemplateMail",entity, String.class);
        }

}
