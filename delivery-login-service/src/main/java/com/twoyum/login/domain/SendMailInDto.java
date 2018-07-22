package com.twoyum.login.domain;

import lombok.Data;

/**
 * 邮件发送输入对象
 */
@Data
public class SendMailInDto {
    private String from;//发送邮件方
    private String to;//接收邮件方
    private String subject;//邮件标题
    private String text;//邮件内容
    private String url;//链接地址
    private String btnText;//按钮文字
}
