package com.twoyum.login.service;

import com.twoyum.login.domain.ValidateInfoInDto;
import com.twoyum.login.domain.ValidateInfoOutDto;

public interface ValidateInfoService {
    ValidateInfoOutDto findValidateInfoByEmail(ValidateInfoInDto validateInfoInDto);
}
