package com.twoyum.login.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ValidateInfoOutDto {
    private String email;
    private String validateCode;
    private Timestamp registerDate;
    private String sign;
}
