package com.twoyum.merchant.response;

import lombok.Data;

@Data
public class Response {
    private Integer status;
    private String message;
    private Object data;
    private String error;
    private String errorCode;
}
