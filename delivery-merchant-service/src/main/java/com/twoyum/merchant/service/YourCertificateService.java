package com.twoyum.merchant.service;

import com.twoyum.merchant.domain.*;

import java.util.List;

/**
 * 证件照片服务层接口
 */
public interface YourCertificateService{
    void updateIdentificationPhoto(IdentificationPhotoInDto identificationPhotoInDto);

    IdentificationPhotoOutDto getIdentificationPhoto(IdentificationPhotoInDto identificationPhotoInDto);

    int updateTaxpayerId(TaxpayerIdInDto taxPerDto);

    TaxpayerIdOutDto getTaxpayerId(TaxpayerIdInDto taxpayerIdInDto);

    int updateManager(ManagerInDto managerInDto);

    ManagerOutDto getManager(ManagerInDto managerInDto);

    int updateMailingAddress(MailingAddressInDto mailingAddressInDto);

    MailingAddressOutDto getMailingAddress(MailingAddressInDto mailingAddressInDto);

    List<CountryOutDto> getCountryList();

}
