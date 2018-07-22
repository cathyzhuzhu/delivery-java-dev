package com.twoyum.commons.model;

import lombok.Data;

import java.util.Date;

/**
 * 文件实体对象
 * @author huangjuan
 * @since 2018/02/05
 */
@Data
public class FileInfo {
    private Long id;//pk

    private String serviceId;//服务ID

    private String keyValue;//KEY值

    private String orgFileName;//原始文件名

    private String curFileName;//当前文件名

    private Date createTime;//创建时间
}