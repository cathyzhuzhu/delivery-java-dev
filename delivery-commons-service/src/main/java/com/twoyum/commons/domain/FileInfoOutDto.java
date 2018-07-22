package com.twoyum.commons.domain;

import lombok.Data;

import java.util.Date;
@Data
public class FileInfoOutDto {
    private String serviceId;//服务ID

    private String keyValue;//KEY值

    private String orgFileName;//原始文件名

    private String curFileName;//当前文件名

    private Date createTime;//创建时间

    private String suffixName;//文件后缀

}
