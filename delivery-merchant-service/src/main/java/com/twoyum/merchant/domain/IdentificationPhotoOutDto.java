package com.twoyum.merchant.domain;

import lombok.Data;

import java.util.Date;

/**
 * 证件照片输出对象
 */
@Data
public class IdentificationPhotoOutDto {
	private String identificationPhotoUrl;//证件照片连接
	private int finishStatus;//状态
}
