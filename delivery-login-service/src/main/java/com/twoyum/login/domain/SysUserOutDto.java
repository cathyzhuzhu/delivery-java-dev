package com.twoyum.login.domain;

import lombok.Data;

import java.util.List;

@Data
public class SysUserOutDto {
//    private String account;
    private String password;
    private String userName;
    private Integer status;
    private String email;
    private List<String> roles;

}
