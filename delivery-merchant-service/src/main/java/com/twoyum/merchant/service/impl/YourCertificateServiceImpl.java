package com.twoyum.merchant.service.impl;

import com.twoyum.merchant.domain.*;
import com.twoyum.merchant.mapper.YourCertificateMapper;
import com.twoyum.merchant.mapper.UserMapper;
import com.twoyum.merchant.service.YourCertificateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 证件照片服务层接口实现
 */
@Service
public class YourCertificateServiceImpl implements YourCertificateService {

    @Autowired
    private YourCertificateMapper yourCertificateMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional
    public void updateIdentificationPhoto(IdentificationPhotoInDto identificationPhotoInDto) {
        userMapper.updateSwitchTypeByEmail(identificationPhotoInDto);
        yourCertificateMapper.updateIdentificationPhoto(identificationPhotoInDto);
    }

    @Override
    public IdentificationPhotoOutDto getIdentificationPhoto(IdentificationPhotoInDto identificationPhotoInDto) {
        return yourCertificateMapper.getIdentificationPhoto(identificationPhotoInDto);
    }

    @Override
    public int updateTaxpayerId(TaxpayerIdInDto taxpayerIdInDto) {
        return yourCertificateMapper.updateTaxpayerId(taxpayerIdInDto);
    }

    @Override
    public TaxpayerIdOutDto getTaxpayerId(TaxpayerIdInDto taxpayerIdInDto) {
        return yourCertificateMapper.getTaxpayerId(taxpayerIdInDto);
    }

    @Override
    public int updateManager(ManagerInDto managerInDto) {
        return yourCertificateMapper.updateManager(managerInDto);
    }

    @Override
    public ManagerOutDto getManager(ManagerInDto managerInDto) {
        return yourCertificateMapper.getManager(managerInDto);
    }

    @Override
    public int updateMailingAddress(MailingAddressInDto mailingAddressInDto) {
        return yourCertificateMapper.updateMailingAddress(mailingAddressInDto);
    }

    @Override
    public MailingAddressOutDto getMailingAddress(MailingAddressInDto mailingAddressInDto) {
          if(!StringUtils.isEmpty(mailingAddressInDto.getCountryId())
                  &&!StringUtils.isEmpty(mailingAddressInDto.getStreet())
                  &&!StringUtils.isEmpty(mailingAddressInDto.getRoomNo())
                  &&!StringUtils.isEmpty(mailingAddressInDto.getCity())
                  &&!StringUtils.isEmpty(mailingAddressInDto.getState())
                  &&!StringUtils.isEmpty(mailingAddressInDto.getPostCode())){
                  mailingAddressInDto.setFinishStatus(1);
          }else{
              mailingAddressInDto.setFinishStatus(0);
          }
        return yourCertificateMapper.getMailingAddress(mailingAddressInDto);
    }

    @Override
    public List<CountryOutDto> getCountryList() {
        return yourCertificateMapper.getCountryList();
    }

}
