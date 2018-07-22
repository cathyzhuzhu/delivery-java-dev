package com.twoyum.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserInDto {
    private String typeCode;
    private int id;
    private String email;
    private String url;
    private String password;
    private String confirmPassword;
    private String sign;
    private String validateCode;
    private Timestamp registerDate;
    private String sid;
    private int enabled;//链接是否已经被调用
    private String from;
}
