package com.twoyum.merchant.mapper;

import com.twoyum.merchant.domain.*;

import java.util.List;

/**
 * 证件照片持久层实现
 */
public interface YourCertificateMapper {
	int updateIdentificationPhoto(IdentificationPhotoInDto identificationPhotoInDto);

	IdentificationPhotoOutDto getIdentificationPhoto(IdentificationPhotoInDto identificationPhotoDto);

	int updateTaxpayerId(TaxpayerIdInDto taxPerDto);

	TaxpayerIdOutDto getTaxpayerId(TaxpayerIdInDto taxpayerIdIDto);

	int updateManager(ManagerInDto managerInDto);

	ManagerOutDto getManager(ManagerInDto managerIDto);

	int updateMailingAddress(MailingAddressInDto mailingAddressInDto);

	MailingAddressOutDto getMailingAddress(MailingAddressInDto mailingAddressInDto);

	List<CountryOutDto> getCountryList();

}