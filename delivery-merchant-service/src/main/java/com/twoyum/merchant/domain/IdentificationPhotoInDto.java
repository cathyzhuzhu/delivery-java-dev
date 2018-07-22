package com.twoyum.merchant.domain;

import lombok.Data;

/**
 * 证件照片输入对象
 */
@Data
public class IdentificationPhotoInDto {
	private String identificationPhotoUrl;//证件照片连接
	private String typeCode;//语言类型
	private String email;//用户邮件地址
	private int switchType;//切换类型
	private String token;
}
