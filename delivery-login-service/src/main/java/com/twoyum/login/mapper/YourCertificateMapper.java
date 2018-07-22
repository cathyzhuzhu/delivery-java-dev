package com.twoyum.login.mapper;

import com.twoyum.login.domain.UserInDto;

/**
 * 证件照片持久层实现
 */
public interface YourCertificateMapper {
	int addYourCertificate(UserInDto userInDto);

}