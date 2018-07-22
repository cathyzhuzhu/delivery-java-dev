package com.twoyum.login.commons;

import lombok.Data;

/**
 *  输出对象
 */
@Data
public class Response{
    private String message;
    private Object data;
    private Integer status;
    private String error;
}
