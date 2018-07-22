package com.twoyum.login.mapper;

import com.twoyum.login.domain.UserInDto;
import com.twoyum.login.domain.UserOutDto;

import java.util.List;

public interface UserMapper {
    /**
     *  新增注册用户
     * @param userInDto 输入对象
     * @return 返回结果
     */
    int addRegisterUser(UserInDto userInDto);

    int findByEmailCount(UserInDto userInDto);

    UserOutDto findUserByEmail(String email);

    int updateUserInfo(UserInDto userInDto);

    int updatePassword(UserInDto userInDto);
}
