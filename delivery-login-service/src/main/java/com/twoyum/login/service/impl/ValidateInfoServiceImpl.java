package com.twoyum.login.service.impl;

import com.twoyum.login.domain.ValidateInfoInDto;
import com.twoyum.login.domain.ValidateInfoOutDto;
import com.twoyum.login.mapper.ValidateInfoMapper;
import com.twoyum.login.service.ValidateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateInfoServiceImpl implements ValidateInfoService {
    @Autowired
    private ValidateInfoMapper validateInfoMapper;
   public ValidateInfoOutDto findValidateInfoByEmail(ValidateInfoInDto validateInfoInDto){
    return validateInfoMapper.findValidateInfoByEmail(validateInfoInDto);
    }
}
