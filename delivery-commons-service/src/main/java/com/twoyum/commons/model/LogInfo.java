package com.twoyum.commons.model;

import lombok.Data;

import java.util.Date;

/**
 * 日志实体对象
 * @author huangjuan
 * @since 2018/02/02
 */
@Data
public class LogInfo {
	private String id;//pk
	private String ipAddress;//ip地址
	private String requestMethod;//请求方法
	private String requestMethodDesc;//请求方法描述
	private String requestBusiness;//请求业务
	private String channel;//渠道
	private String operator;//操作人 
	private Date operateTime;//操作时间
	private String serviceId;//服务ID
}
