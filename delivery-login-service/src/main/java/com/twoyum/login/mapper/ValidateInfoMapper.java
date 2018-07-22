package com.twoyum.login.mapper;

import com.twoyum.login.domain.ValidateInfoInDto;
import com.twoyum.login.domain.ValidateInfoOutDto;

public interface ValidateInfoMapper {
    int addValidateInfo(ValidateInfoInDto validateCodeInfoInDto);
    ValidateInfoOutDto findValidateInfoByEmail(ValidateInfoInDto validateInfoInDto);
}
