package com.twoyum.commons.commons;

import lombok.Data;

@Data
public class Response {
    private Integer status;
    private String message;
    private Object data;
    private String error;
}
