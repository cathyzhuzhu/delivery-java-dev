package com.twoyum.login.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserOutDto {
    private String typeCode;
    private String email;
    private String userName;
    private String url;
    private int switchType;
    private String sign;
    private String validateCode;
    private Timestamp registerDate;
    private int enabled;//链接是否已经被调用
}
