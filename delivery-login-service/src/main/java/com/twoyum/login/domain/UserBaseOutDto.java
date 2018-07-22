package com.twoyum.login.domain;

import lombok.Data;

@Data
public class UserBaseOutDto {
    private String account;
    private String userName;
    private Integer status;
    private String roleCode;
}
