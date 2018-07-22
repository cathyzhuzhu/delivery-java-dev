package com.twoyum.controller;

import com.twoyum.domain.SendMailInDto;
import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.util.Map;


@RestController
@RequestMapping(value = "/sendMail")
public class SendMailController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private VelocityEngine velocityEngine;

    @RequestMapping(value = "/sendSimpleMail" ,method = RequestMethod.POST)
	public String sendSimpleMail(@RequestBody SendMailInDto sendMailInDto) throws Exception {
    	System.out.println("-----------------------02机子");
		SimpleMailMessage message = new SimpleMailMessage();
		String from = sendMailInDto.getFrom();
		String to = sendMailInDto.getTo();
		String subject = sendMailInDto.getSubject();
		String text = sendMailInDto.getText();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
//		message.setCc("");
		mailSender.send(message);
		return "--------------02";
	}

	@RequestMapping(value = "/sendTemplateMail" ,method = RequestMethod.POST)
	public String sendTemplateMail(@RequestBody SendMailInDto sendMailInDto) throws Exception {
		String from = sendMailInDto.getFrom();
		String to = sendMailInDto.getTo();
		String subject = sendMailInDto.getSubject();
		String text = sendMailInDto.getText();//文字说明
		String url = sendMailInDto.getUrl();//链接地址
		String btnText = sendMailInDto.getBtnText();
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(from,"2Yum");
		helper.setTo(to);
		helper.setSubject(subject);

		Map<String, Object> model = new HashedMap();
		model.put("text", text);
		model.put("url",url);
		model.put("btnText",btnText);
		String content = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, "template.vm", "UTF-8", model);
		helper.setText(content, true);

		mailSender.send(mimeMessage);
		return "--------------02";
	}
}
