package com.twoyum.login.domain;

import lombok.Data;

@Data
public class ResourceOutDto {
    private String sysResourceId;
    private String resourceName;
    private String parentCode;
    private String path;
    private Integer sortNo;
}
