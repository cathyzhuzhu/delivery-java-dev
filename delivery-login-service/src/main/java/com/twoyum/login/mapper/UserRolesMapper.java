package com.twoyum.login.mapper;

import com.twoyum.login.domain.UserRolesInDto;

public interface UserRolesMapper {
    /**
     *  新增注册用户
     * @param userRolesInDto 输入对象
     * @return 返回结果
     */
    int addUserRoles(UserRolesInDto userRolesInDto);
}
